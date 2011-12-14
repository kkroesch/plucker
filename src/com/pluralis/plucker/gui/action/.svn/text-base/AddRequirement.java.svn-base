/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.RequirementsEditor;

import de.kroesch.util.GuiUtils;


public class AddRequirement extends AbstractAction {

  private Application application;
  
  public AddRequirement(Application application) {
    this.application = application;

    putValue(Action.NAME, "Add Requirement");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("addtask.gif"));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift R"));
  }

  public void actionPerformed(ActionEvent event) {
    new RequirementsEditor(application).openDialog(true);
  }
}