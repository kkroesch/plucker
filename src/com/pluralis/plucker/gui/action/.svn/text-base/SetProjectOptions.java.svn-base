/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.ProjectPropertiesEditor;

public class SetProjectOptions extends AbstractAction {
  private Application application;
  
  public SetProjectOptions(Application application) {
    this.application = application;
    putValue(Action.NAME, "Project Options");
    //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift A"));
  }

  public void actionPerformed(ActionEvent event) {
    ProjectPropertiesEditor editor = new ProjectPropertiesEditor(application);
    editor.openDialog(true);
  }
}