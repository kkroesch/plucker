/*
 * Components.java
 *
 * Created on 12. Juni 2006, 14:46
 */

package com.pluralis.plucker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTable;

import com.pluralis.plucker.gui.action.AddTask;
import com.pluralis.plucker.gui.dialog.ActorEditor;
import com.pluralis.plucker.gui.dialog.ChangeRequestEditor;
import com.pluralis.plucker.gui.dialog.RequirementsEditor;
import com.pluralis.plucker.gui.dialog.TaskDialog;
import com.pluralis.plucker.gui.dialog.TestcaseEditor;
import com.pluralis.plucker.gui.dialog.UseCaseEditor;
import com.pluralis.plucker.model.Activity;
import com.pluralis.plucker.model.Actor;
import com.pluralis.plucker.model.ChangeRequest;
import com.pluralis.plucker.model.Requirement;
import com.pluralis.plucker.model.TestCase;
import com.pluralis.plucker.model.UseCase;
import com.pluralis.plucker.model.Activity.Status;
import com.pluralis.plucker.util.TableMouseListener;

import de.kroesch.util.GuiUtils;


/**
 *
 * @author  karsten.kroesch
 */
public class Components implements ComponentCreator {

  private JXTable tableActors;
  private JXTable tableUsecases;
  private JXTable tableRequirements;
  private JXTable tableTestCases;
  
  private JXTable tableActivities;
  private JXTable tableChangeRequests;
  
  private JLabel statisticsLabel;
  
  protected Application application;
  
  public Components(Application application) {
    this.application = application;
  }
  
  public JComponent create() {
    JPanel component = new JPanel(new BorderLayout(2,2));
    
    JTabbedPane tabbedPane = new javax.swing.JTabbedPane();

    tableRequirements = new JXTable();
    tableRequirements.setModel(application.getProject().getRequirementsTableModel());
    GuiUtils.columnSize(tableRequirements, new int[] {42, 100, 350});
    tableRequirements.addMouseListener(new RequirementMouseListener(tableRequirements));
    tabbedPane.addTab("Requirements", new JScrollPane(tableRequirements));
    
    tableActors = new JXTable();
    tableActors.setModel(application.getProject().getActorTableModel());
    GuiUtils.columnSize(tableActors, new int[] {42, 100, 350, 60});
    tableActors.addMouseListener(new ActorMouseListener(tableActors));
    tableActors.getColumnExt(3).getSorter().setComparator(new Actor.ComplexityComparator());
    tabbedPane.addTab("Actors", new JScrollPane(tableActors));
    
    tableUsecases = new JXTable();
    tableUsecases.addMouseListener(new UcMouseListener(tableUsecases));
    tableUsecases.setModel(application.getProject().getUsecaseModel());
    GuiUtils.columnSize(tableUsecases, new int[] {42, 100, 350, 60});
    tableUsecases.getColumnExt(3).getSorter().setComparator(new UseCase.ComplexityComparator());
    tabbedPane.addTab("Use Cases", new JScrollPane(tableUsecases));

    tableTestCases = new JXTable();
    tableTestCases.setModel(application.getProject().getTestCaseTableModel());
    GuiUtils.columnSize(tableTestCases, new int[] {42, 100, 350});
    tableTestCases.addMouseListener(new TestCaseMouseListener(tableTestCases));
    tabbedPane.addTab("TestCases", new JScrollPane(tableTestCases));

    tableChangeRequests = new JXTable();
    tableChangeRequests.setModel(application.getProject().getChangeRequestTableModel());
    GuiUtils.columnSize(tableChangeRequests, new int[] {42, 350});
    tableChangeRequests.addMouseListener(new ChangeRequestMouseListener(tableChangeRequests));
    tabbedPane.addTab("Change Requests", new JScrollPane(tableChangeRequests));
    
    tabbedPane.addTab("Activities", createActivities());

    component.add(tabbedPane, BorderLayout.CENTER);
    
    component.add(GuiUtils.createHintLabel("Hint: press CTRL+F to find in name and description"), BorderLayout.SOUTH);

    return component;
  }
  
  private JComponent createActivities() {
    JPanel panel = new JPanel(new BorderLayout(6,6));
    panel.setBorder(new EmptyBorder(6,6,6,6));
    
    tableActivities = new JXTable(application.getProject().getActivityTableModel());
    GuiUtils.columnSize(tableActivities, new int[] {42, 100, 250, 90, 60});
    
    tableActivities.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        label.setText(formatter.format(value));
        label.setForeground(Color.red);
        label.setIcon(GuiUtils.readImageIcon("warn.gif"));
        
        return label;
      }
    });
    
    tableActivities.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JCheckBox cb = new JCheckBox(value.toString(), Status.Closed.toString().equals(value.toString()));
        cb.setOpaque(false);
        return cb;
      }
    });
    
    tableActivities.addMouseListener(new ActivityMouseListener(tableActivities));
    
    panel.add(new JScrollPane(tableActivities), BorderLayout.CENTER);
    
    JPanel linkpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    linkpanel.add(new JXHyperlink(new AddTask(application)));
    panel.add(linkpanel, BorderLayout.NORTH);
    
    return panel;
  }
  
  private JTable createTable() {
    JXTable table = new JXTable();
    table.setColumnControlVisible(true);
    
    return table;
  }
  
  //
  // MOUSE LISTENERS
  //
  
  private class UcMouseListener extends TableMouseListener {

    public UcMouseListener(JXTable table) {
      super(table);
    }

    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      UseCase uc = (UseCase) application.getProject().getUsecaseModel().getValueAt(row);
      UseCaseEditor editor = new UseCaseEditor(uc, application);
      editor.openDialog(false);
    }

    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }
  
  private class ActorMouseListener extends TableMouseListener {

    public ActorMouseListener(JXTable table) {
      super(table);
    }

    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      Actor actor = (Actor) application.getProject().getActorTableModel().getValueAt(row);
      ActorEditor editor = new ActorEditor(actor, application);
      editor.openDialog(false);
    }

    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }
  
  private class RequirementMouseListener extends TableMouseListener {

    public RequirementMouseListener(JXTable table) {
      super(table);
    }

    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      Requirement req = (Requirement) application.getProject().getRequirementsTableModel().getValueAt(row);
      RequirementsEditor editor = new RequirementsEditor(req, application);
      editor.openDialog(false);
    }

    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }

  private class TestCaseMouseListener extends TableMouseListener {

    public TestCaseMouseListener(JXTable table) {
      super(table);
    }

    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      TestCase test = (TestCase) application.getProject().getTestCaseTableModel().getValueAt(row);
      TestcaseEditor editor = new TestcaseEditor(test, application);
      editor.openDialog(false);
    }

    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }

  private class ActivityMouseListener extends TableMouseListener {

    public ActivityMouseListener(JXTable table) {
      super(table);
    }

    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      Activity act = (Activity) application.getProject().getActivityTableModel().getValueAt(row);
      TaskDialog editor = new TaskDialog(act, application);
      editor.openDialog(false);
    }

    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }
  
  private class ChangeRequestMouseListener extends TableMouseListener {
    
    public ChangeRequestMouseListener(JXTable table) {
      super(table);
    }
    
    @Override
    public void doubleClick() {
      int row = table.convertRowIndexToModel(table.getSelectedRow());
      ChangeRequest cr = (ChangeRequest) application.getProject().getChangeRequestTableModel().getValueAt(row);
      ChangeRequestEditor editor = new ChangeRequestEditor(cr, application);
      editor.openDialog(false);
    }
    
    @Override
    public void popupMenu() {
      // TODO Auto-generated method stub
    }
  }

}