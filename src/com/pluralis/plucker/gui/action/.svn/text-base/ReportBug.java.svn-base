package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;


public class ReportBug extends AbstractAction {
  public static final String HELP_SET = "help/Master.hs";
  
  private Application application;
  
  public ReportBug(Application application) {
    this.application = application;
    putValue(Action.NAME, "Report Bug");
  }

  public void actionPerformed(ActionEvent event) {
    application.userInfo("Point your browser to http://192.168.2.4/flyspray");
  }
}