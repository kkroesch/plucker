/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.model.Project;

public class Search extends AbstractAction {
  private Application application;
  
  public Search(Application application) {
    this.application = application;

    putValue(Action.NAME, "Search");
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control F"));
  }
  
  public void actionPerformed(ActionEvent event) {
    JDialog dialog = new JDialog(application.rootWindow(), "Search", true);
    JTextField searchPanel = new JTextField();
    dialog.getContentPane().add(searchPanel, BorderLayout.NORTH);
    
    Box resultPanel = Box.createVerticalBox();
    dialog.getContentPane().add(resultPanel, BorderLayout.CENTER);
    
    JPanel buttonDialog = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    buttonDialog.add(new JButton(new StartSearch(application.getProject(), resultPanel, searchPanel.getText())));
    buttonDialog.add(new JButton(new CancelDialog(dialog)));
    
    dialog.getContentPane().add(buttonDialog, BorderLayout.SOUTH);
    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }
  
  private static class StartSearch extends AbstractAction {
    private Project project;
    private Box resultPanel;
    private String sexp;
    
    public StartSearch(Project project, Box resultPanel, String sexp) {
      this.project = project;
      this.sexp = sexp;
      this.resultPanel = resultPanel;
      putValue(Action.NAME, "Start");
    }
    
    public void actionPerformed(ActionEvent event) {
      resultPanel.removeAll();
      resultPanel.add(new JLabel("Search returned no Results."));
      
      resultPanel.getParent().repaint();
    }    
  }
}