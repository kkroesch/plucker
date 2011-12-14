/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;

public class ExportProject extends AbstractAction {
  private Application application;
  
  public ExportProject(Application application) {
    this.application = application;
  }

  public ExportProject() {
    putValue(Action.NAME, "Export");
  }
  
  public void actionPerformed(ActionEvent event) {
    JDialog dialog = new JDialog(application.rootWindow(), "Export", true);
    dialog.getContentPane().add(createExportOptions(), BorderLayout.CENTER);
    
    JPanel buttonDialog = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    buttonDialog.add(new JButton("Export"));
    buttonDialog.add(new JButton(new CancelDialog(dialog)));
    
    dialog.getContentPane().add(buttonDialog, BorderLayout.SOUTH);
    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }
  

  private JComponent createExportOptions() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 150dlu:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Export Settings");

    builder.append(new JCheckBox("Requirements Specification", true));
    builder.append(new JCheckBox("Software Estimation", true));
    builder.append(new JCheckBox("Test Cases", true));
    builder.append(new JCheckBox("Project Status Report", false));
    builder.append("File Format", new JComboBox(new String[]{"PDF", "CSV", "XML"}));
    
        
    builder.appendSeparator();
    JPanel panel = builder.getPanel();
    panel.setBorder(new EmptyBorder(2, 6, 2, 12));

    return panel;
  }
  
}