/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;

public class ViewClientLog extends AbstractAction {

  private Application application;
  
  public ViewClientLog(Application application) {
    this.application = application;
  }

  public ViewClientLog() {
    putValue(Action.NAME, "Client Log");
    //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control F10"));
  }

  public void actionPerformed(ActionEvent event) {
    application.setTopic(new LogViewer("client.log"), "Client Log");
  }
  
  private static class LogViewer implements ComponentCreator {
    private String logfile;

    public LogViewer(String logfile) {
      this.logfile = logfile;
    }

    public JComponent create() {
      JTextArea text = new JTextArea();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(logfile));
        for (String line = ""; line != null; line=reader.readLine()) {
          text.append(line + "\n");
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      
      return new JScrollPane(text);
    }
  }
}
