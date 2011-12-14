package com.pluralis.plucker.plugin;

import com.pluralis.plucker.gui.Application;

/**
 * Specifies the interface for a plugin.
 * 
 * @author karsten.kroesch
 *
 */
public interface Plugin extends Runnable {
  
  /**
   * Gets a name of the plugin for menu item.
   */
  String getName();
  
  /**
   * A short description for the plugin.
   */
  String getDescription();

  /**
   * Version description for plugin. 
   */
  String getVersion();
  
  /**
   * Is called at instantiation of the plugin.
   * 
   * @param application Interface allowing the plugin to communicate with the application.
   */
  void setApplication(Application application);
  
}
