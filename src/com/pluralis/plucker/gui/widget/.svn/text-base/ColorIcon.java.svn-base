package com.pluralis.plucker.gui.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon
    implements Icon {

  private Color color;
  private int width, height;

  public ColorIcon() {
    this(Color.gray, 50, 15);
  }

  public ColorIcon(Color color, int w, int h) {
    this.color = color;
    this.height = h;
    this.width = w;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width - 1, height - 1);
    g.setColor(color);
    g.fillRect(x + 1, y + 1, width - 2, height - 2);
  }

  public Color getColor() {
    return (color);
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getIconWidth() {
    return (width);
  }

  public int getIconHeight() {
    return (height);
  }
}
