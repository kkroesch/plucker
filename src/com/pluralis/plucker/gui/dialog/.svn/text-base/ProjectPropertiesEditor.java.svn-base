package com.pluralis.plucker.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.Project;


public class ProjectPropertiesEditor implements ComponentCreator, Editor {

  private PresentationModel adapter;
  
  private Application application;
  
  public ProjectPropertiesEditor(Application application) {
    this.application = application;
    adapter = new PresentationModel(application.getProject());
  }

  public JComponent create() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 150dlu:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Project Settings");

    builder.append("Project Name", 
        BasicComponentFactory.createTextField(adapter.getBufferedModel("name")));
    
    JTextArea descriptionText = new JTextArea(4, 65);
    Bindings.bind(descriptionText, adapter.getBufferedModel("description"));
    builder.append("Project Scope", new JScrollPane(descriptionText));

    builder.appendSeparator("Related Documents");
    builder.append(new FileList(((Project)adapter.getBean()).getRelatedDocuments(), application).create(), 3);

    JPanel panel = builder.getPanel();
    panel.setBorder(new EmptyBorder(2, 6, 2, 12));

    return panel;
  }

  public void add() {
    adapter.triggerCommit();
    application.rootWindow().setTitle(application.getApplicationName() + " :: " + application.getProject().getName());
  }

  public void save() {
    adapter.triggerCommit();
    application.rootWindow().setTitle(application.getApplicationName() + " :: " + application.getProject().getName());
  }

  public void cancel() {
    adapter.triggerFlush();
  }

  public void openDialog(boolean create) {
    JDialog dialog = new JDialog(application.rootWindow(), "Project Settings", true);
    dialog.getContentPane().add(create(), BorderLayout.CENTER);
    
    JPanel buttonDialog = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    buttonDialog.add(new JButton(new OkDialog(dialog, this)));
    buttonDialog.add(new JButton(new CancelDialog(dialog)));
    
    dialog.getContentPane().add(buttonDialog, BorderLayout.SOUTH);
    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }
}
