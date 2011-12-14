package com.pluralis.plucker.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel {

  public StatusBar() {
    setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
    setBorder(new BevelBorder(BevelBorder.LOWERED));
    setText(" ");
  }

  public void setText(String text) {
    removeAll();
    add(new JLabel(text));
  }
}
