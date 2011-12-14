/*
 * Created on 30.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class BeanUtil {
  
  public static PropertyDescriptor createDescriptor(
      String display, Class type, String property) 
  {
    PropertyDescriptor desc = null;
    try {
      desc = new PropertyDescriptor(property, type);
    } catch (IntrospectionException e) {
      throw new RuntimeException("Cannot create descriptor for " + property);
    }
    desc.setDisplayName(display);

    return desc;
  }

  public static PropertyDescriptor createDescriptor(
      String display, Class type, String property, Class editor) 
  {
    PropertyDescriptor desc = createDescriptor(display, type, property);
    desc.setPropertyEditorClass(editor);
    
    return desc;
  }

}
