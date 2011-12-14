package com.pluralis.plucker.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.gui.FocusRequester;
import com.pluralis.plucker.gui.TestApplication;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.gui.widget.CheckList;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.ChangeRequest;

public class ChangeRequestEditor implements ComponentCreator, Editor {

  public static void main(String[] args) {
    new ChangeRequestEditor(new TestApplication()).openDialog(false);
  }
  
  private PresentationModel adapter;
  
  private Application application;
  
  private CheckList checklist;
  
  private JDialog dialog;
  
  public ChangeRequestEditor(Application application) {
    this(new ChangeRequest(), application);
  }

  public ChangeRequestEditor(ChangeRequest changeRequest, Application application) {
    this.application = application;
    adapter = new PresentationModel(changeRequest);
  }

  public JComponent create() {
    JTabbedPane tabPane = new JTabbedPane();
    tabPane.addTab("Details", createOverview());
    tabPane.addTab("Impact", createImpact());
    
    return tabPane;
  }
    
  private JComponent createOverview() {   
    FormLayout layout = new FormLayout("right:pref, 6dlu, 50dlu:grow, 6dlu, right:pref, 6dlu, 50dlu:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Change Request");

    builder.append("ID", BasicComponentFactory.createLabel(adapter.getBufferedModel("id")));
    JTextField summaryField = BasicComponentFactory.createTextField(adapter.getBufferedModel("summary"));
    dialog.addWindowListener(new FocusRequester(summaryField));
    builder.append("Summary", summaryField);
    builder.append("Type", BasicComponentFactory.createComboBox(
        new SelectionInList(ChangeRequest.types, adapter.getBufferedModel("type"))));
    builder.append("Severity", BasicComponentFactory.createComboBox(
        new SelectionInList(ChangeRequest.severities, adapter.getBufferedModel("severity"))));
    builder.append("Status", BasicComponentFactory.createComboBox(
        new SelectionInList(ChangeRequest.stati, adapter.getBufferedModel("status"))));
    builder.append("Priority", BasicComponentFactory.createIntegerField(adapter.getBufferedModel("priority")));
    
    JXDatePicker duePicker = new JXDatePicker();
    Bindings.bind(duePicker.getEditor(), adapter.getBufferedModel("dueDate"));
    builder.append("Due Date", duePicker);
    builder.append("Due Version", BasicComponentFactory.createComboBox(
        new SelectionInList(ChangeRequest.stati, adapter.getBufferedModel("dueVersion"))));
    
    JTextArea detailText = new JTextArea(4, 65);
    Bindings.bind(detailText, adapter.getBufferedModel("description"));
    builder.append("Description", new JScrollPane(detailText), 5);

    builder.append("Rough Est.", BasicComponentFactory.createIntegerField(adapter.getBufferedModel("roughEstimation")));
    builder.append("Detailed Est.", BasicComponentFactory.createIntegerField(adapter.getBufferedModel("detailedEstimation")));
    
    builder.appendSeparator("Related Documents");
    builder.append(
        new FileList(((ChangeRequest)adapter.getBean()).getRelatedDocuments(), application).create(), 7);

    JPanel panel = builder.getPanel();
    panel.setBorder(new EmptyBorder(2, 6, 2, 12));

    return panel;
  }
  
  private JComponent createImpact() {
    checklist = new CheckList(application.getProject().getUsecase());
    return new JScrollPane(checklist);
  }
  
  public void cancel() {
    adapter.triggerFlush();
  }

  public void add() {
    ChangeRequest cr = (ChangeRequest) adapter.getBean();
    cr.getAffectedUseCases().addAll(checklist.checkedItems());

    adapter.triggerCommit();
    application.getProject().generateId(cr);
    application.getProject().getChangeRequest().add(cr);
    application.getProject().getChangeRequestTableModel().fireTableDataChanged();

  }

  public void save() {
    ChangeRequest cr = (ChangeRequest) adapter.getBean();
    cr.getAffectedUseCases().addAll(checklist.checkedItems());

    adapter.triggerCommit();
    application.getProject().getActorTableModel().fireTableDataChanged();
  }

  public void openDialog(boolean create) {
    dialog = new JDialog(application.rootWindow(), "Edit Change Request", true);
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
}
