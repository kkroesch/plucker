/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.Components;

public class ViewComponents extends AbstractAction {

  private Application application;
  
  public ViewComponents(Application application) {
    this.application = application;
  }

  public void actionPerformed(ActionEvent event) {
    Components comp = new Components(application);
    application.setTopic(comp, event.getActionCommand());
  }
}
