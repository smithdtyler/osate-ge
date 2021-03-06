/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package org.osate.ge.internal.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.ge.internal.services.SystemInstanceLoadingService;
import org.osate.ge.internal.util.Log;
import org.osate.workspace.WorkspacePlugin;

public class DefaultSystemInstanceLoadingService implements SystemInstanceLoadingService {
	public static class ContextFunction extends SimpleServiceContextFunction<SystemInstanceLoadingService> {
		@Override
		protected SystemInstanceLoadingService createService(final IEclipseContext context) {
			return new DefaultSystemInstanceLoadingService();
		}		
	}
	
	private final Object lock = new Object();
	
	private final Map<IProject, Map<String, IPath>> projectToKeyToPathMap = new HashMap<>();
	private final Map<IPath, String> pathToKeyMap = new HashMap<IPath, String>();
	private final Set<IFile> pendingInstanceModels = new HashSet<IFile>(); // For populating the maps once a request is made. This is needed to prevent from triggering a modification during a resource change notifications
	
	public DefaultSystemInstanceLoadingService() {
		// Listen for resource changes. Keep mapping up to date.
		ResourcesPlugin.getWorkspace().addResourceChangeListener(new IResourceChangeListener() {
			@Override
			public void resourceChanged(final IResourceChangeEvent event) {
				if(event.getType() == IResourceChangeEvent.POST_CHANGE) {
					try {
						synchronized(lock) {
							event.getDelta().accept(deltaVisitor);
						}
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}					
		         }
			}
			
			private IResourceDeltaVisitor deltaVisitor = new IResourceDeltaVisitor() {
				public boolean visit(final IResourceDelta delta) {
            		// Check AADL Instance Model Files
            		final IResource resource = delta.getResource();
            		if (resource.getType() == IResource.FILE && WorkspacePlugin.INSTANCE_FILE_EXT.equalsIgnoreCase(resource.getFileExtension())) {           			
            			// Update mappings
    	            	if(delta.getKind() == IResourceDelta.REMOVED) {
    	            		removeFromMaps((IFile)resource);
    	            	} else {
   	            			// Remove then re-add the file
   	            			removeFromMaps((IFile)resource);
   	            			pendingInstanceModels.add((IFile)resource);
    	            	}
            		}
		   
            		return true;
	            }
			};
		});

		// Add existing instance files to the pending files collection
		synchronized(lock) {
			for(final IFile instanceFile : findInstanceFiles(ResourcesPlugin.getWorkspace().getRoot(), null)) {
				pendingInstanceModels.add(instanceFile);
			}
		}
	}

	@Override
	public SystemInstance loadSystemInstance(final IProject project, final String key) {
		synchronized(lock) {
			// Process pending files
			for(final IFile pendingInstanceFile : pendingInstanceModels) {
       			addToMaps(pendingInstanceFile);
			}
			pendingInstanceModels.clear();

			final Map<String, IPath> keyToPathMap = projectToKeyToPathMap.get(project);
			final IPath path;
			if(keyToPathMap == null) {				
				path = null;
			} else {
				path = keyToPathMap.get(key);
			}

			final SystemInstance result = path == null ? null : loadSystemInstance(path);
			return result;
		}
	}

	/**
	 * Returns a unique key for a system instance. Returns null if the component classifier can not be retrieved.
	 */
	public String getKey(final SystemInstance systemInstance) {
		final ComponentClassifier cc = systemInstance.getComponentClassifier();
		if(cc == null) {
			return null;
			
		}
		final String qualifiedName = cc.getQualifiedName();
		if(qualifiedName == null) {
			return null;
		}
		
		return qualifiedName.toLowerCase();
	}
	
	private List<IFile> findInstanceFiles(final IContainer container, List<IFile> instanceFiles) {
		if(instanceFiles == null) {
			instanceFiles = new ArrayList<IFile>();
		}
		
		try {
			if(container.isAccessible()) {
				for(final IResource resource : container.members()) {
					 if (resource instanceof IContainer) {
				         findInstanceFiles((IContainer)resource, instanceFiles);
				     } else if (resource instanceof IFile) {
				         final IFile file = (IFile) resource;
				         if(WorkspacePlugin.INSTANCE_FILE_EXT.equalsIgnoreCase(file.getFileExtension())) {
				              instanceFiles.add(file);
				         }
				     }
				}
			}
		} catch (CoreException e) {
			Log.error("Error finding instance models", e);
			throw new RuntimeException(e);
		}
		
		return instanceFiles;
	}
	
	private void addToMaps(final IFile instanceFile) {
		final IPath fullPath = instanceFile.getFullPath();
		final IProject project = instanceFile.getProject();
		if(project != null) {
			final SystemInstance systemInstance = loadSystemInstance(fullPath);
			if(systemInstance != null) {
				final String key = getKey(systemInstance);
				if(key != null) {
					pathToKeyMap.put(fullPath, key);
					
					// Ge they key to path map for the project
					Map<String, IPath> keyToPathMap = projectToKeyToPathMap.get(project);
					if(keyToPathMap == null) {
						keyToPathMap = new HashMap<>();
						projectToKeyToPathMap.put(project, keyToPathMap);
					}
					
					keyToPathMap.put(key, fullPath);
				}
			}
		}
	}
	
	private void removeFromMaps(final IFile instanceFile) {
		final IPath instancePath = instanceFile.getFullPath();
		final String key = pathToKeyMap.get(instancePath);
		if(key != null) {
			pathToKeyMap.remove(instancePath);
			
			// Remove it from the key to path map
			final IProject project = instanceFile.getProject();
			if(project != null) {
				final Map<String, IPath> keyToPathMap = projectToKeyToPathMap.get(project);
				if(keyToPathMap != null) {
					keyToPathMap.remove(key);
				}
			}
		}
		
		// Remove it from the pending instance files collection in case it is awaiting processing
		pendingInstanceModels.remove(instanceFile);
	}
	
	private SystemInstance loadSystemInstance(final IPath fullInstancePath) {
		final URI uri = URI.createPlatformResourceURI(fullInstancePath.toString(), true);
		final ResourceSet resourceSet = new ResourceSetImpl();
		try {
			final Resource resource = resourceSet.getResource(uri, true);
			
			// If the resource was loaded, retrieve and return the SystemInstance
			if(resource != null) {
				for(final EObject obj : resource.getContents()) {
					if(obj instanceof SystemInstance) {
						return (SystemInstance)obj;			
					}											
				}
			}
		} catch(final Exception ex) {
			// Suppress the exception but print to the console. The exception is suppressed because it could happen under normal circumstances. For example
			// the exception could be thrown if there is a system instance anywhere in the workspace that cannot be loaded.
			ex.printStackTrace();
		}
		
		return null;
	}
}
