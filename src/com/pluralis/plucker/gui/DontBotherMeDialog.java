package com.pluralis.plucker.gui;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DontBotherMeDialog {

  public static void main(String[] args) {
    int answer = new DontBotherMeDialog(new TestApplication(), "wtf").showConfirmMessage("WTF?");
    System.out.println("Answer is " + answer);
    System.exit(0);
  }
  
  private Application app;
  
  private String confirmOption;

  public DontBotherMeDialog(Application app, String confirmOption) {
    this.app = app;
    this.confirmOption = confirmOption;
  }

  public int showConfirmMessage(String message) {
    if (app.options().isConfirm(confirmOption)) return JOptionPane.OK_OPTION;
    
    JPanel panel = new JPanel(new BorderLayout(2,42));
    panel.add(new JLabel(message), BorderLayout.CENTER);
    
    JCheckBox checker = new JCheckBox("Don't show this dialog in future.");
    panel.add(checker, BorderLayout.SOUTH);
    int answer = JOptionPane.showConfirmDialog(app.rootWindow(), panel);
    
    if (checker.isSelected()) app.options().neverConfirm(confirmOption);
    app.options().store();
    
    return answer;
  }
}
