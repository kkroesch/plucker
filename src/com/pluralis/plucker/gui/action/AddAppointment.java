/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.TerminDialog;

import de.kroesch.util.GuiUtils;


public class AddAppointment extends AbstractAction {

  private Application application;

  public AddAppointment(Application application) {
    this.application = application;
    putValue(Action.NAME, "New Appointment");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("addtask.gif"));
  }

  public void actionPerformed(ActionEvent event) {
    new TerminDialog(application).setVisible(true);
  }
}
