package com.pluralis.plucker.gui.dialog;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
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
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.Requirement;
import com.pluralis.plucker.util.FlagDependency;

/*
 * Created on 19.11.2005 by Karsten $Id$
 */

public class RequirementsEditor extends JDialog implements Editor, ComponentCreator {
  
  private FlagDependency textDependency = new FlagDependency();
  
  private JTextArea descField = new JTextArea(4, 20);
  
  private PresentationModel adapter;
  
  private Application application;
  
  public RequirementsEditor(Requirement req, Application application) {
    super(application.rootWindow(), "Requirement", true);
    this.application = application;
    adapter = new PresentationModel(req);
  }
  
  public RequirementsEditor(Application application) {
    this(new Requirement(), application);
  }
  

  public void openDialog(boolean create) {
    Box box = Box.createVerticalBox();
    
    box.add(create());
    
    box.setBorder(new EmptyBorder(2, 6, 2, 12));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(box, BorderLayout.CENTER);
    
    getContentPane().add(createButtonPanel(create), BorderLayout.SOUTH);
    
    pack();
    application.centerDialog(this);

    setVisible(true);
  }

  public void add() {
    adapter.triggerCommit();
    application.getProject().generateId((Requirement) adapter.getBean());
    application.getProject().getRequirement().add((Requirement) adapter.getBean());
    application.getProject().getRequirementsTableModel().fireTableDataChanged();
    dispose();
  }

  public void save() {
    adapter.triggerCommit();
    application.getProject().getRequirementsTableModel().fireTableDataChanged();
    dispose();
  }
  
  public void cancel() {
    adapter.triggerFlush();
  }

  public JComponent create() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 150dlu:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.setBorder(new EmptyBorder(6,6,6,6));

    builder.append("ID", BasicComponentFactory.createLabel(adapter.getBufferedModel("id")));
    builder.append("Name", textDependency.registerText(
        BasicComponentFactory.createTextField(adapter.getBufferedModel("name"))));
    
    builder.appendSeparator("Details");
    JTextArea descriptionText = new JTextArea(4, 65);
    Bindings.bind(descriptionText, adapter.getBufferedModel("description"));
    builder.append("Description", new JScrollPane(descriptionText));
    
    builder.appendSeparator("Related Documents");
    builder.append(new FileList(((Requirement)adapter.getBean()).getRelatedDocuments(), application).create(), 3);
    
    return builder.getPanel();
  }

  private Component createButtonPanel(boolean create) {
    JPanel buttonPanel = new JPanel();
    
    if (create)
      buttonPanel.add(
          textDependency.registerComponent(new JButton(new CreateDialog(this, this)), false));
    else
      buttonPanel.add(new JButton(new OkDialog(this, this)));

    buttonPanel.add(new JButton(new CancelDialog(this)));
    
    return buttonPanel;
  }
}
