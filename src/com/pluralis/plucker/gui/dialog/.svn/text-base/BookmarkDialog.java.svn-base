package com.pluralis.plucker.gui.dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Main;
import com.pluralis.plucker.util.FlagDependency;

import de.kroesch.util.GuiUtils;

/*
 * Created on 19.11.2005 by Karsten $Id$
 */

public class BookmarkDialog extends JDialog {

  private FlagDependency textDependency = new FlagDependency();
  
  public BookmarkDialog() {
    //super(app.rootWindow(), "Edit Bookmark", true);

    Box box = Box.createVerticalBox();
    
    box.add(createDetails());
    
    box.setBorder(new EmptyBorder(2, 6, 2, 12));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(box, BorderLayout.CENTER);
    
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    
    pack();
  }

  private Component createDetails() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 75dlu:grow",
        "");

    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.appendSeparator("New Bookmark");
    builder.append("Icon", new JLabel("Text"));
    builder.append(createIconSelector(), 
        textDependency.registerText(new JTextField()));
    
    builder.appendSeparator("Action");
    builder.append("Action:", new JComboBox());
    builder.append("Param 1:", new JTextField());
    builder.append("Param 2:", new JTextField());
    builder.append("Param 3:", new JTextField());
    
    builder.append("", new JCheckBox("All users"));
    builder.appendSeparator();
    return builder.getPanel();
  }

  private Component createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(
        textDependency.registerComponent(new JButton("Save", 
        GuiUtils.readImageIcon("save_edit.gif")), false));
    buttonPanel.add(new JButton("Cancel", GuiUtils.readImageIcon("delete.gif")));
    
    return buttonPanel;
  }
  
  private JComboBox createIconSelector() {
    URL url = Main.class.getClassLoader().getResource("images/icons.lst");
    
    ListCellRenderer lcr = new ListCellRenderer() {
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel l = new JLabel();
        l.setIcon(GuiUtils.readImageIcon(value.toString()));
        //l.setText(value.toString());
        l.setBackground(isSelected ? Color.black : Color.white);
        l.setForeground(isSelected ? Color.white : Color.black);
        return l;
      }
    };
    
    List<String> iconsList = new ArrayList<String>();
    Collections.sort(iconsList);
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while ((line = reader.readLine()) != null) iconsList.add(line);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
    JComboBox cbox = new JComboBox(iconsList.toArray());
    cbox.setRenderer(lcr);
    
    return cbox;
  }
}
