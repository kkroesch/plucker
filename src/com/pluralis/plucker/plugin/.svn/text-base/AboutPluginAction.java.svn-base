package com.pluralis.plucker.plugin;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.pluralis.plucker.gui.Application;

/**
 * Runs non-visual plugins.
 * 
 * @author karsten.kroesch
 *
 */
public class AboutPluginAction extends AbstractAction {

  private Plugin plugin;
  
  private Application application;
  
  public AboutPluginAction(Plugin plugin, Application application) {
    this.plugin = plugin;
    this.application = application;
    
    putValue(Action.NAME, plugin.getName());
    putValue(Action.SHORT_DESCRIPTION, plugin.getDescription());
  }

  public void actionPerformed(ActionEvent e) {
    Box box = Box.createVerticalBox();
    JLabel title = new JLabel(plugin.getName());
    title.setFont(new Font("Helvetica", Font.BOLD, 14));
    box.add(title);
    
    box.add(new JLabel(plugin.getDescription()));
    box.add(new JLabel("Version " + plugin.getVersion()));
    
    box.setBorder(new EmptyBorder(10,10,10,10));
    
    JOptionPane.showMessageDialog(application.rootWindow(), box);
  }

}
