package com.pluralis.plucker.gui.dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.widget.ColorComboBox;
import com.pluralis.plucker.util.FlagDependency;

import de.kroesch.util.GuiUtils;


public class TerminDialog extends JDialog {

  private static final String[] hours12 = new String[] {
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
  };

  private static final String[] minutes = new String[] {
    "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
  };
  
  private static final Color[] colors = new Color[] {
    Color.blue, Color.cyan, Color.darkGray, Color.green, Color.red
  };
  
  public static String[] categories = new String[] {
    "<No Categories>"
  };
  
  public static String[] intervals = new String[] {
    "Minutes", "Hours", "Days", "Weeks"
  };
  
  public static String[] repeats = new String[] {
    "Once", "Daily", "Monthly", "Mon-Fri"
  };
  
  public static String[] employees = new String[] {
    "Alice", "Bob", "Caesar", "Eve", "Withfield", "Martin"
  };
  
  private FlagDependency textDependency = new FlagDependency();
  
  public TerminDialog(Application application) throws HeadlessException {
    super(application.rootWindow(), "Edit Appointment", true);

    Box settingsBox = Box.createVerticalBox();
    
    settingsBox.add(createDateChooser());
    settingsBox.add(createDetails());
    settingsBox.add(createSettings());
    settingsBox.add(createRemainder());
    settingsBox.add(createRecurrence());
    
    settingsBox.setBorder(new EmptyBorder(2, 6, 2, 12));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(settingsBox, BorderLayout.CENTER);
    
    getContentPane().add(createParticipantsList(), BorderLayout.EAST);
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    
    pack();
  }

  private Component createParticipantsList() {
    Box box = Box.createVerticalBox();
    box.setBorder(new TitledBorder("Participants"));
    JList listView = new JList(employees);
    box.add(new JScrollPane(listView));
    
    Box buttonBox = Box.createHorizontalBox();
    buttonBox.add(new JButton("Add..."));
    Box.createHorizontalGlue();
    buttonBox.add(new JButton("Remove"));
    box.add(buttonBox);
    
    return box;
  }

  private Component createDateChooser() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 25dlu, 6dlu, 25dlu, 6dlu, 75dlu:grow, 6dlu, 75dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Date and time settings");
    FlagDependency dependency = new FlagDependency();
    FlagDependency dependency2 = new FlagDependency();
    
    builder.append("Begin:", 
        dependency.registerComponent(new JComboBox(hours12)), 
        dependency.registerComponent(new JComboBox(minutes)), 
        dependency.registerComponent(new JCheckBox("PM")));
    
    builder.append(
        dependency2.registerCheckBox(new JCheckBox("Change date", false)));
    builder.nextLine();
    
    builder.append("Duration:", 
        dependency.registerComponent(new JComboBox(hours12)), 
        dependency.registerComponent(new JComboBox(minutes)));
    
    builder.append(dependency.registerCheckBox(new JCheckBox("Whole day")), 
        dependency2.registerComponent(new JXDatePicker(),false));
    
    return builder.getPanel();
  }

  private Component createDetails() {
    FormLayout layout = new FormLayout(
        "150dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Details");
    builder.append(new JScrollPane(
        textDependency.registerText(new JTextArea(4, 20))));
    
    return builder.getPanel();
  }

  private Component createSettings() {
    FormLayout layout = new FormLayout(
        "50dlu:grow, 6dlu, 50dlu:grow, 6dlu, 50dlu:grow, 6dlu, 50dlu:grow, 6dlu, 50dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Settings");
    
    builder.append(new JCheckBox("To-Do"));
    builder.append(new JCheckBox("Holiday"));
    builder.append(new JCheckBox("Half day"));
    builder.append(new JCheckBox("Vacancy"));
    builder.append(new JCheckBox("Private"));
    builder.nextLine();
    
    builder.append("Color:", new ColorComboBox());
    builder.append("Category:", new JComboBox(categories), new JCheckBox("Alarm"));
    
    return builder.getPanel();
  }

  private Component createRemainder() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 25dlu, 6dlu, 50dlu:grow, 6dlu, 50dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Remainder");
    builder.append("Remind ", new JSpinner(), new JComboBox(intervals), new JLabel("before"));
    
    return builder.getPanel();
  }

  private Component createRecurrence() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 25dlu, 6dlu, 50dlu:grow, 6dlu, 50dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Recurrence");
    builder.append("Repeat:", new JSpinner(), new JComboBox(intervals), new JCheckBox("Endless"));

    return builder.getPanel();
  }

  private Component createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(
        textDependency.registerComponent(new JButton("Save", 
        GuiUtils.readImageIcon("save_edit.gif")), false));
    buttonPanel.add(
        textDependency.registerComponent(new JButton("Save Template", 
        GuiUtils.readImageIcon("saveas_edit.gif")), false));
    buttonPanel.add(new JButton("Cancel", GuiUtils.readImageIcon("delete.gif")));
    
    return buttonPanel;
  }
}
