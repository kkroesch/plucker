/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.DontBotherMeDialog;

public class FileExit extends AbstractAction {
  
  private Application application;
  
  public FileExit(Application application) {
    this.application = application;
    putValue(Action.NAME, "Exit");
  }

  public void actionPerformed(ActionEvent event) {
    if (application.getProject().isDirty()) {
      int answer = JOptionPane.showConfirmDialog(application.rootWindow(), 
          "Project file has changed. Save Changes?", "QUESTION", JOptionPane.YES_NO_CANCEL_OPTION);
      
      if (answer == JOptionPane.CANCEL_OPTION) return;
//      if (answer == JOptionPane.YES_OPTION)
//        new Store(application).save(application.getProject());
    }

    application.options().store();
    System.exit(0);
  }
}