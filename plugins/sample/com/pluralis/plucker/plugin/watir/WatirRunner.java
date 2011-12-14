package com.pluralis.plucker.plugin.watir;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.plugin.Plugin;


public class WatirRunner implements Plugin {

  private Application app;
  
  public String getName() {
    return "WatirRunner";
  }

  public String getDescription() {
    return "Runs a Watir Script";
  }

  public String getVersion() {
    return "0.1";
  }

  public void run() {
    app.setTopic(new ComponentCreator(){
      public JComponent create() {
        return new JLabel("Hi! I'm the WATIR plugin.");
      }
    }, "Plugin");
  }

  public void setApplication(Application application) {
    this.app = application;
  }
  
}
