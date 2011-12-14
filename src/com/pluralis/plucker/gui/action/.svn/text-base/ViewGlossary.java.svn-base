/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.GlossarEditor;

public class ViewGlossary extends AbstractAction {

  private Application application;
  
  public ViewGlossary(Application application) {
    this.application = application;
  }

  public void actionPerformed(ActionEvent event) {
    GlossarEditor comp = new GlossarEditor(application.getProject().getGlossary());
    application.setTopic(comp, event.getActionCommand());
  }
}
