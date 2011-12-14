package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.pluralis.plucker.gui.Application;

import de.kroesch.util.GuiUtils;


public class HelpContent extends AbstractAction {
  public static final String HELP_SET = "help/Master.hs";
  
  private Application application;
  
  private String context;

  public HelpContent(Application application) {
    this(application, "index");
  }
  
  public HelpContent(Application application, String context) {
    this.application = application;
    this.context = context;
    
    putValue(Action.NAME, "Help");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("help.gif"));
  }

  public void actionPerformed(ActionEvent event) {
    try {
      File file = new File(GuiUtils.resourceFile("help/" + context + ".html").toURI());
      
      Process proc = Runtime.getRuntime().exec("cmd.exe /c \"" + file.getAbsolutePath() + "\"");
    } catch (IOException ex) {
      application.handle(ex);
    } catch (URISyntaxException e) {
      application.handle(e);
    }
  }
}