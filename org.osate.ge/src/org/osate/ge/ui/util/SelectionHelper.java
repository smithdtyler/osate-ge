// Based on SelectionHelper form the OSATE2-Ocarina plugin
/*
* Use of the OSATE2-Ocarina plugin is subject to the terms of the license set forth
* at http://www.eclipse.org/legal/cpl-v10.html.
*
* Note: Contributions by user philip.alldredge@uah.edu were performed under contract W31P4Q-05-A-031 for the US Army. 
* These contributions are covered by US Government Unlimited Rights per DFARS 252.227-7014. You are entitled to use this software under the conditions of the Common Public License.
*/
package org.osate.ge.ui.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
public class SelectionHelper {
	private static EObjectAtOffsetHelper eObjectAtOffsetHelper = new EObjectAtOffsetHelper();
	
	private static ISelection getSelection() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		IWorkbenchPart part = page.getActivePart();
		IEditorPart activeEditor = page.getActiveEditor();
		if (activeEditor == null) {
			throw new RuntimeException("Unexpected case. Unable to get active editor");
		}

		final ISelection selection;
		if (part instanceof ContentOutline) {
			selection = ((ContentOutline) part).getSelection();
		} else {
			selection = getXtextEditor().getSelectionProvider().getSelection();
		}

		return selection;
	}

	// Based on code in: org.osate.xtext.aadl2.ui.handlers.InstantiateHandler
	// Gets the selected model object
	public static EObject getSelectedObject() {
		return getEObjectFromSelection(getSelection());
	}

	private static EObject getEObjectFromSelection(final ISelection selection) {
		return getXtextEditor().getDocument().readOnly(
				new IUnitOfWork<EObject, XtextResource>() {
					public EObject exec(XtextResource resource)
							throws Exception {
						EObject targetElement = null;
						if (selection instanceof IStructuredSelection) {
							IStructuredSelection ss = (IStructuredSelection) selection;
							Object eon = ss.getFirstElement();
							if (eon instanceof EObjectNode) {
								targetElement = ((EObjectNode) eon)
										.getEObject(resource);
							}
						} else {
							final int offset = ((ITextSelection) selection).getOffset();
							targetElement = eObjectAtOffsetHelper.resolveContainedElementAt(resource, offset);

							// Check if the node for the semantic element that was retrieved by the offset helper starts after the line number of the node
							// retrieved using the offset. If it is, return the container. This prevents returning a classifier when the select is actually in
							// whitespace before the classifier.
							final IParseResult parseResult = resource.getParseResult();
							if(targetElement != null && parseResult != null) {
								final ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);
								final INode targetElementNode = NodeModelUtils.getNode(targetElement);
								if(leaf.getStartLine() < targetElementNode.getStartLine()) {
									targetElement = targetElement.eContainer();
								}
							}
							
							// If there isn't a selected element, that usually means the selection is outside of the root package. Get the first EObject in the resource
							if(targetElement == null && resource.getContents().size() > 0) {
								targetElement = resource.getContents().get(0);	
							}
						}

						return targetElement;
					}
				});
	}

	private static XtextEditor getXtextEditor() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		IEditorPart activeEditor = page.getActiveEditor();
		if (activeEditor == null) {
			throw new RuntimeException("Unexpected case. Unable to get active editor");
		}

		XtextEditor xtextEditor = (XtextEditor)activeEditor.getAdapter(XtextEditor.class);
		if (xtextEditor == null) {
			throw new RuntimeException("Unexpected case. Unable to get Xtext editor");
		}

		return xtextEditor;
	}
	
	/**
	 * Returns the current project
	 * @return the project that contains the current selection.
	 */
	public static IProject getProject() {
		EObject selectedObject = getSelectedObject();
		if(selectedObject == null) {
			throw new RuntimeException("Unable to retrieve project. Selection is null.");
		}
		
		return getProject(selectedObject.eResource());
	}
	
	public static IProject getProject(final Resource resource) {
		return getProject(resource.getURI());
	}
	
	public static IProject getProject(final URI resourceUri) {
		final IPath projectPath = new Path(resourceUri.toPlatformString(true)).uptoSegment(1);
		final IResource projectResource = ResourcesPlugin.getWorkspace().getRoot().findMember(projectPath);
		if(projectResource instanceof IProject) {
			return (IProject)projectResource;
		}

		throw new RuntimeException("Unable to receive project. Resource is of type " + projectResource.getClass().getName());
	}
}