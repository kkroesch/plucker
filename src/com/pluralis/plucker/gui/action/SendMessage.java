/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.MessageDialog;

public class SendMessage extends AbstractAction {
  private Application application;
  
  public SendMessage(Application application) {
    this.application = application;
  }

  public void actionPerformed(ActionEvent event) {
    new MessageDialog(application).setVisible(true);
  }
}
