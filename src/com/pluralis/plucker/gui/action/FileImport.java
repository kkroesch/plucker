/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;

public class FileImport extends AbstractAction {
  
  private Application application;

  public FileImport(Application application) {
    this.application = application;
    putValue(Action.NAME, "Import…");
    setEnabled(false);
  }

  public void actionPerformed(ActionEvent event) {
    throw new UnsupportedOperationException();
  }
}