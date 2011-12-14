package com.pluralis.plucker.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.widget.FileTextField;

import de.kroesch.util.GuiUtils;


public class OptionDialog extends JDialog {

  private Application application;
  
  public OptionDialog(Application application) {
    super(application.rootWindow(), "Options", true);

    Box box = Box.createVerticalBox();
    
    box.add(createDetails());
    
    box.setBorder(new EmptyBorder(2, 6, 2, 12));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(box, BorderLayout.CENTER);
    
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    
    pack();
    application.centerDialog(this);
  }

  private Component createDetails() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 150dlu:grow", 
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("Files");

    JComponent pdField = new FileTextField(this);
    builder.append("Project Directory", pdField);
    JComponent jrField = new FileTextField(this);
    builder.append("JUnit Report", jrField);
    
    return builder.getPanel();
  }

  private Component createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(new JButton("Save", GuiUtils.readImageIcon("save_edit.gif")), false);
    buttonPanel.add(new JButton(new CancelDialog(this)), false);
    
    return buttonPanel;
  }
}
