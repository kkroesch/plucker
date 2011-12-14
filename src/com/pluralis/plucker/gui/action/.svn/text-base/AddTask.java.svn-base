/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.TaskDialog;

import de.kroesch.util.GuiUtils;


public class AddTask extends AbstractAction {

  private Application application;
  
  public AddTask(Application application) {
    this.application = application;
    
    putValue(Action.NAME, "Add Activity");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("topic.gif"));
  }

  public void actionPerformed(ActionEvent event) {
    new TaskDialog(application).openDialog(true);
  }
}
