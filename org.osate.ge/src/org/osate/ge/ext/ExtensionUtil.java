package org.osate.ge.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.eclipse.jface.resource.ImageDescriptor;
import org.osate.ge.ext.annotations.Description;
import org.osate.ge.ext.annotations.Icon;
import org.osate.ge.ext.annotations.Id;

public class ExtensionUtil {
	public static String getId(final Object tool) {
		return (String)getFieldValue(tool, Id.class);
	}
	
	public static ImageDescriptor getIcon(final Object tool) {
		return (ImageDescriptor)getFieldValue(tool, Icon.class);
	}
	
	public static String getDescription(final Object tool) {
		return (String)getFieldValue(tool, Description.class);
	}
	
	public static Object getFieldValue(final Object tool, final Class<? extends Annotation> annotationClass) {
		for(final Field field : tool.getClass().getDeclaredFields()) {
			if(field.isAnnotationPresent(annotationClass)) {
				try {
					return field.get(tool);
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
