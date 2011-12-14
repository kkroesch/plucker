package com.pluralis.plucker.plugin;

import javax.swing.Action;

import com.pluralis.plucker.gui.ComponentCreator;

/**
 * Interface for plugins which are visible in the GUI.
 * 
 * @author karsten.kroesch
 *
 */
public interface VisualPlugin extends Plugin {

  /**
   * A Swing action for menu or button. 
   */
  Action menuItem();
  
  /**
   * Visual component which can be displayed in the GUI.  
   */
  ComponentCreator visualComponent();
  
}
