/**
 * 
 */
package com.pluralis.plucker.gui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.pluralis.plucker.model.NamedEntry;

public class NamedItemRenderer implements ListCellRenderer {

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JPanel renderer = new JPanel(new BorderLayout());
  
    //Icon icon = ((NamedEntry) value).getIcon();
    //renderer.add(new JLabel(icon), BorderLayout.WEST);
    
    JLabel label = new JLabel(((NamedEntry)value).getName());
    label.setBorder(new EmptyBorder(3,5,1,1));
    renderer.add(label, BorderLayout.CENTER);
    
    renderer.setBackground(isSelected ? Color.darkGray : Color.white);
    label.setForeground(isSelected ? Color.white : Color.black);
    return renderer;
  }
}