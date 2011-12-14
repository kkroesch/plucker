/**
 * 
 */
package com.pluralis.plucker.gui.widget;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.pluralis.plucker.gui.Desktop;

public class Separator extends JPanel {
  public Separator(String text, Icon icon) {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel label = new JLabel(text, icon, SwingConstants.LEFT);
    label.setFont(new Font("Verdana", Font.BOLD, 16));
    label.setForeground(Color.GRAY);
    add(label);
    JSeparator sep = new JSeparator();
    add(sep);
    
    setBackground(Desktop.BACKGROUND_COLOR);
  }
}