package com.pluralis.plucker.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class ProjectChangeListener implements PropertyChangeListener {
  
  private Logger log = Logger.getLogger("Project");

  private Project project;
  
  public ProjectChangeListener(Project project) {
    this.project = project;
  }

  public void propertyChange(PropertyChangeEvent evt) {
    log.fine("Property change: " + evt.getPropertyName() + " from " + evt.getOldValue() + " to " + evt.getNewValue());
    if (project.isDirty()) return;
    
    log.info("Project changed state to dirty.");
    project.setDirty(true);
  }
}
