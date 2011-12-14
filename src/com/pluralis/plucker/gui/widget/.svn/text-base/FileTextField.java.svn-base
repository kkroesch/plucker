package com.pluralis.plucker.gui.widget;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.kroesch.util.GuiUtils;


public class FileTextField extends JPanel {

  public final JTextField pathField = new JTextField();
  private JButton browseButton;
  
  public String getPath() {
    return pathField.getText();
  }
  
  public void setPath(String path) {
    pathField.setText(path);
  }
  
  @Override
  public void setEnabled(boolean enabled) {
    pathField.setEnabled(enabled);
    browseButton.setEnabled(enabled);
  }
  
  @Override
  public boolean isEnabled() {
    return pathField.isEnabled() && browseButton.isEnabled();
  }

  public FileTextField(Window parent) {
    super(new BorderLayout(), true);
    
    add(pathField, BorderLayout.CENTER);
    browseButton = new JButton(new ChooseFileAction(pathField, parent));
    browseButton.setBorder(new EmptyBorder(1,6,1,1));
    add(browseButton, BorderLayout.EAST);
  }
  
  
  private static class ChooseFileAction extends AbstractAction {

    private JTextField fileField;
    private Window parent;
    
    public ChooseFileAction(JTextField fileField, Window parent) {
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("search.gif"));
      this.fileField = fileField;
      this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(parent);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        fileField.setText(chooser.getSelectedFile().getPath());
      }
    }
  }
}
