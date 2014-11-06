package com.pluralis.plucker.gui.dialog;
import static com.pluralis.plucker.model.Activity.STATUS_VALUES;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.model.Activity;

import de.kroesch.util.GuiUtils;

/*
 * Created on 19.11.2005 by Karsten $Id$
 */

public class TaskDialog implements ComponentCreator, Editor {

  private Logger log = Logger.getLogger("TaskDialog");
  
  private Application application;
  
  private JDialog dialog;
  
  private PresentationModel adapter;
  
  public TaskDialog(Application application) {
    this(new Activity(), application);
  }
  
  public TaskDialog(Activity activity, Application application) {
    this.application = application;
    adapter = new PresentationModel(activity);
  }
  
  public void add() {
    adapter.triggerCommit();
    Activity act = (Activity) adapter.getBean();
    application.getProject().generateId(act);
    log.info("Added Activity " + act);
    application.getProject().getActivity().add(act);
    application.getProject().getActivityTableModel().fireTableDataChanged();
  }

  public void openDialog(boolean create) {
    dialog = new JDialog(application.rootWindow(), "Add Task", true);

    dialog.getContentPane().setLayout(new BorderLayout(6, 6));
    dialog.getContentPane().add(create(), BorderLayout.CENTER);
    dialog.getContentPane().add(createButtonPanel(create), BorderLayout.SOUTH);

    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }

  public void save() {
    adapter.triggerCommit();
  }
  
  public void cancel() {
    adapter.triggerFlush();
  }

  public JComponent create() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, default:grow", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    
    builder.append("Activity", BasicComponentFactory.createLabel(adapter.getBufferedModel("id")));
    builder.append("Name", BasicComponentFactory.createTextField(adapter.getBufferedModel("name")));
    builder.append("Assigned to", BasicComponentFactory.createComboBox(
        new SelectionInList(application.getProject().assignmentModel(), 
            adapter.getBufferedModel("assignment"))));
    
    builder.appendSeparator("Details");
    
    builder.append("Comment");
    JTextArea description = BasicComponentFactory.createTextArea(adapter.getBufferedModel("description"));
    description.setRows(4);
    builder.append(new JScrollPane(description));
    
    builder.appendSeparator();
    JXDatePicker duePicker = new JXDatePicker();
    Bindings.bind(duePicker.getEditor(), adapter.getBufferedModel("dueDate"));
    builder.append("Due Date", new JXDatePicker());
    JXDatePicker startPicker = new JXDatePicker();
    Bindings.bind(startPicker.getEditor(), adapter.getBufferedModel("started"));
    builder.append("Start Date", startPicker);
    builder.append("Status", BasicComponentFactory.createComboBox(
        new SelectionInList(STATUS_VALUES, adapter.getBufferedModel("status"))));

    builder.append("Responsible", BasicComponentFactory.createTextArea(
        adapter.getBufferedModel("author")));

    builder.append(createTimeManagement(), 3);
    
    builder.appendSeparator();
    return builder.getPanel();
  }
  
  private Component createTimeManagement() {
    FormLayout layout = new FormLayout("right:pref, 6dlu, 50dlu, 6dlu:grow, right:pref, 6dlu, 50dlu", "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Time Management");
    
    builder.append("Estimated Hours", BasicComponentFactory.createIntegerField(
        adapter.getBufferedModel("estimatedHours")));
    builder.append("Real Hours", BasicComponentFactory.createIntegerField(
        adapter.getBufferedModel("realHours")));
    builder.append("Remaining Hours", BasicComponentFactory.createIntegerField(
        adapter.getBufferedModel("remainingHours")));

    return builder.getPanel();
  }

  private Component createButtonPanel(boolean create) {
    JPanel buttonPanel = new JPanel();
    if (create)
      buttonPanel.add(new JButton(new CreateDialog(dialog, this)));
    else
      buttonPanel.add(new JButton(new OkDialog(dialog, this)));
    buttonPanel.add(new JButton(new CancelDialog(dialog)), GuiUtils.readImageIcon("delete.gif"));
    
    return buttonPanel;
  }
}
