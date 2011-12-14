package com.pluralis.plucker.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.gui.TestApplication;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.model.Actor;

public class ModelEditor implements ComponentCreator, Editor {

  private PresentationModel adapter;
  
  private Application application;
  
  private String dialogTitle = "Default title";

  public ModelEditor() {
    this(new TestApplication());
    openDialog(false);
  }

  public ModelEditor(Application application) {
    this(new Actor(), application, "");
  }

  public ModelEditor(Model model, Application application, String dialogTitle) {
    this.application = application;
    adapter = new PresentationModel(model);
    this.dialogTitle = dialogTitle;
  }

  public JComponent create() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 150dlu:grow", "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);

    for (PropertyDescriptor descriptor : properties()) {
      try {
        builder.append(descriptor.getName(),
            BasicComponentFactory.createTextField(adapter.getBufferedModel(descriptor.getName())));
      } catch (Exception e) {
        // Skip
      }
    }

    JPanel panel = builder.getPanel();
    panel.setBorder(new EmptyBorder(2, 6, 2, 12));

    return panel;
  }
  
  public void cancel() {
    adapter.triggerFlush();
  }

  public void add() {
    adapter.triggerCommit();
  }

  public void save() {
    adapter.triggerCommit();
  }

  public void openDialog(boolean create) {
    JDialog dialog = new JDialog(application.rootWindow(), dialogTitle, true);
    dialog.getContentPane().add(create(), BorderLayout.CENTER);
    
    JPanel buttonDialog = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    if (create)
      buttonDialog.add(new JButton(new CreateDialog(dialog, this)));
    else
      buttonDialog.add(new JButton(new OkDialog(dialog, this)));
    buttonDialog.add(new JButton(new CancelDialog(dialog)));
    
    dialog.getContentPane().add(buttonDialog, BorderLayout.SOUTH);
    dialog.pack();
    application.centerDialog(dialog);

    dialog.setVisible(true);
  }

  public PresentationModel getAdapter() {
    return adapter;
  }

  public void setAdapter(PresentationModel adapter) {
    this.adapter = adapter;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public String getDialogTitle() {
    return dialogTitle;
  }

  public void setDialogTitle(String dialogTitle) {
    this.dialogTitle = dialogTitle;
  }
  
  private PropertyDescriptor[] properties() {
    try {
      BeanInfo info = Introspector.getBeanInfo(adapter.getBean().getClass());
      return info.getPropertyDescriptors();
    } catch (IntrospectionException e) {
      throw new RuntimeException(e);
    }
  }
}
