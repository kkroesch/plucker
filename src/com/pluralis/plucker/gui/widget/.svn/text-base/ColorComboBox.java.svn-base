package com.pluralis.plucker.gui.widget;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class ColorComboBox
    extends JComboBox {

  private Color[] colors;
  private int baseColor[] = {
      0, 64, 128, 192, 255};

  public ColorComboBox() {
    super();

    int clength = baseColor.length;
    int tablength = (clength * clength * clength);
    colors = new Color[tablength];

    int l = 0;
    for (int i = 0; i < clength; i++) {
      for (int j = 0; j < clength; j++) {
        for (int k = 0; k < clength; k++) {
          colors[l] = new Color(baseColor[i], baseColor[j], baseColor[k]);
          l++;
        }
      }
    }

    DefaultComboBoxModel model = new DefaultComboBoxModel(colors);
    this.setModel(model);
    setRenderer(new ColorRenderer());
  }

  public Color getColor() {
    return (colors[this.getSelectedIndex()]);
  }

  public void setSelectedColor(Color c) {
    int i = 0;
    for (; i < colors.length; i++) {
      if (colors[i].equals(c)) {
        break;
      }
    }
    if (i == colors.length) {
      i = 0;
    }
    setSelectedIndex(i);
  }

  class ColorRenderer
      extends JLabel
      implements ListCellRenderer {

    private ColorIcon icon = new ColorIcon();
    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
      icon.setColor( (Color) value);
      setIcon(icon);

      if (isSelected) {
        setBorder(blackBorder);
      }
      else {
        setBorder(emptyBorder);

      }
      return (this);
    }
  }

}
