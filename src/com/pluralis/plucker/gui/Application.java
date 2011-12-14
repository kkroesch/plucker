package com.pluralis.plucker.gui;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.pluralis.plucker.model.ClientOptions;
import com.pluralis.plucker.model.Project;
import com.pluralis.plucker.plugin.PluginRegistry;


public interface Application {

  PluginRegistry pluginRegistry();
  
  JFrame rootWindow();
  
  void centerDialog(JDialog dialog);
  
  MenuBar menu();
  
  JComponent navigationPanel();
  
  void setTopic(ComponentCreator component, String title);
  
  void status(String status);
  
  ClientOptions options();
  
  void userInfo(String message);
  
  void userWarn(String message);
  
  void userError(String message);
  
  Project getProject();
  
  void setProject(Project project);
  
  void handle(Throwable t);
  
  String getVersion();

  String getApplicationName();
}
