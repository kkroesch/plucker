/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;

public class ShowTopic extends AbstractAction {

  private ComponentCreator topic;
  private Application application;  

  public ShowTopic(Application application, ComponentCreator topic) {
    this.application = application;
    this.topic = topic;
  }

  public void actionPerformed(ActionEvent event) {
      application.setTopic(topic, event.getActionCommand());
  }
}
