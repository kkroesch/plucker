package com.pluralis.plucker.plugin;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Runs non-visual plugins.
 * 
 * @author karsten.kroesch
 *
 */
public class PluginAction extends AbstractAction {

  private Plugin plugin;
  
  public PluginAction(Plugin plugin) {
    this.plugin = plugin;
    
    putValue(Action.NAME, plugin.getName());
    putValue(Action.SHORT_DESCRIPTION, plugin.getDescription());
  }

  public void actionPerformed(ActionEvent e) {
    plugin.run();
  }

}
