package com.pluralis.plucker.gui.dialog;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.TestCase;
import com.pluralis.plucker.util.ClassFinder;

/*
 * Created on 19.11.2005 by Karsten $Id$
 */

public class TestcaseEditor implements Editor, ComponentCreator {
  
  private JDialog dialog;
  
  private PresentationModel adapter;
  
  private Application application;
  
  public TestcaseEditor(Application application) {
    this(new TestCase(), application);
  }
  
  public TestcaseEditor(TestCase testCase, Application application) {
    this.application = application;
    adapter = new PresentationModel(testCase);
  }

  public void cancel() {
    adapter.triggerFlush();
  }

  public void add() {
    adapter.triggerCommit();
    application.getProject().getTestCase().add((TestCase)adapter.getBean());
    application.getProject().getTestCaseTableModel().fireTableDataChanged();
    dialog.dispose();
  }

  public void openDialog(boolean create) {
    dialog = new JDialog(application.rootWindow(), "Testcase", true);
    Box box = Box.createVerticalBox();
    
    box.add(createDetails());
    
    box.setBorder(new EmptyBorder(2, 6, 2, 12));
    dialog.getContentPane().setLayout(new BorderLayout());
    dialog.getContentPane().add(box, BorderLayout.CENTER);
    
    dialog.getContentPane().add(createButtonPanel(create), BorderLayout.SOUTH);
    
    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }

  public void save() {
    adapter.triggerCommit();
    application.getProject().getTestCaseTableModel().fireTableDataChanged();
    dialog.dispose();
  }
  
  public JComponent create() {
    return createDetails();
  }

  private JComponent createDetails() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 150dlu:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.setBorder(new EmptyBorder(6,6,6,6));

    builder.append("Name", BasicComponentFactory.createTextField(adapter.getBufferedModel("name")));
    
    builder.appendSeparator("Details");
    
    List testClasses = new ArrayList();
    testClasses.addAll(new ClassFinder().collectTests());
    JComboBox classField = BasicComponentFactory.createComboBox(
        new SelectionInList(testClasses, adapter.getBufferedModel("testClass"))
    );
    builder.append("Test Class", classField);
    
    builder.appendSeparator("Related Documents");
    builder.append(new FileList(((TestCase)adapter.getBean()).getRelatedDoc(), application).create(), 3);
    
    
    return builder.getPanel();
  }

  private Component createButtonPanel(boolean create) {
    JPanel buttonPanel = new JPanel();
    if (create)
      buttonPanel.add(new JButton(new CreateDialog(dialog, this)));
    else
      buttonPanel.add(new JButton(new OkDialog(dialog, this)));
    buttonPanel.add(new JButton(new CancelDialog(dialog)));
    
    return buttonPanel;
  }
}
