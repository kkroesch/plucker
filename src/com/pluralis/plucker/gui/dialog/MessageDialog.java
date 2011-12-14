package com.pluralis.plucker.gui.dialog;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.util.FlagDependency;

import de.kroesch.util.GuiUtils;

/*
 * Created on 19.11.2005 by Karsten $Id$
 */

public class MessageDialog extends JDialog {

  
  public static String[] employees = new String[] {
    "Alice", "Bob", "Caesar", "Eve", "Withfield", "Martin"
  };
  
  private FlagDependency textDependency = new FlagDependency();
  
  public MessageDialog(Application application) {
    super(application.rootWindow(), "Edit Message", true);

    Box box = Box.createVerticalBox();
    
    box.add(createDetails());
    
    box.setBorder(new EmptyBorder(2, 6, 2, 12));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(box, BorderLayout.CENTER);
    getContentPane().add(createParticipantsList(), BorderLayout.EAST);
    
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    application.centerDialog(this);
    pack();
  }

  private Component createDetails() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 150dlu:grow",
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Message");
    builder.append("Subject: ", new JTextField());
    builder.append("Message: ", new JScrollPane(
        textDependency.registerText(new JTextArea(4, 20))));

    return builder.getPanel();
  }
  
  private Component createParticipantsList() {
    Box box = Box.createVerticalBox();
    box.setBorder(new TitledBorder("Participants"));
    JList listView = new JList();
    box.add(new JScrollPane(listView));
    
    Box buttonBox = Box.createHorizontalBox();
    buttonBox.add(new JButton("Add"));
    buttonBox.add(new JButton("Remove"));
    box.add(buttonBox);
    
    return box;
  }
  
  private Component createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(
        textDependency.registerComponent(new JButton("Send",
        GuiUtils.readImageIcon("passed.gif")), false));
    buttonPanel.add(
        textDependency.registerComponent(new JButton("Save Template", 
        GuiUtils.readImageIcon("saveas_edit.gif")), false));
    buttonPanel.add(new JButton(new CancelDialog(this)));
    
    return buttonPanel;
  }
}
