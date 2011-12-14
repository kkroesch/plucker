/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.ChangeRequestEditor;

import de.kroesch.util.GuiUtils;


public class AddChangeRequest extends AbstractAction {
  private Application application;
  
  public AddChangeRequest(Application application) {
    this.application = application;
    putValue(Action.NAME, "Add Change Request");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("refresh.gif"));
  }

  public void actionPerformed(ActionEvent event) {
    ChangeRequestEditor editor = new ChangeRequestEditor(application);
    editor.openDialog(true);
  }
}