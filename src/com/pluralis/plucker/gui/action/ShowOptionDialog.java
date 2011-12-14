/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.OptionDialog;

public class ShowOptionDialog extends AbstractAction {
  private Application application;
  
  public ShowOptionDialog(Application application) {
    this.application = application;
    putValue(Action.NAME, "Options");
  }

  public void actionPerformed(ActionEvent event) {
    JDialog dialog = new OptionDialog(application);
    application.centerDialog(dialog);
    dialog.pack();
    dialog.setVisible(true);
  }
}
