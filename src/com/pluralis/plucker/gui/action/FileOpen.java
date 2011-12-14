/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.Components;

import de.kroesch.util.GuiUtils;

public class FileOpen extends AbstractAction {
  
  private Application application;
  
  public FileOpen(Application application) {
    this.application = application;
    putValue(Action.NAME, "Open…");
  }

  public void actionPerformed(ActionEvent event) {
    
    JFileChooser chooser = new JFileChooser();

    FileFilter filter = new ProjectFileFilter();
    chooser.setCurrentDirectory(application.options().getLastProjectLocation());
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(application.rootWindow());
    if(returnVal != JFileChooser.APPROVE_OPTION) return;

    try {
      GuiUtils.setWaitCursor(application.rootWindow());
      //application.setProject(new Store(application).load(chooser.getSelectedFile().getAbsolutePath()));
      application.rootWindow().setTitle(application.getApplicationName() + " :: " + application.getProject().getName());
    } finally {
      GuiUtils.resetCursor();
    }
    application.setTopic(new Components(application), "Components");
      
    application.options().setLastProjectLocation(chooser.getSelectedFile().getParentFile());
    application.status("Loaded " + chooser.getSelectedFile().getName());
  }
  
  public static class ProjectFileFilter extends FileFilter {
    @Override
    public String getDescription() {
      return "Plucker Project Files";
    }
    @Override
    public boolean accept(File file) {
      return (file.isDirectory() || file.getName().endsWith(".xml"));
    }
  }
}