/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.test.TestRunner;

public class ViewTestRunner extends AbstractAction {

  public Application application;
  
  public ViewTestRunner(Application application) {
    this.application = application;
    putValue(Action.NAME, "Test Runner");
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F12"));
  }
  
  public void actionPerformed(ActionEvent event) {
    application.setTopic(new TestRunner(application), "Test Runner");
  }
}