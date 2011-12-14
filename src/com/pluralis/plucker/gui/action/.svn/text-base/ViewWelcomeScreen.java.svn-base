/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.WelcomeScreen;

public class ViewWelcomeScreen extends AbstractAction {

  private Application application;

  public ViewWelcomeScreen(Application application) {
    putValue(Action.NAME, "Welcome");
    this.application = application;
  }


  public void actionPerformed(ActionEvent event) {
    WelcomeScreen welcome = new WelcomeScreen(application);
    application.setTopic(welcome, event.getActionCommand());
  }
}
