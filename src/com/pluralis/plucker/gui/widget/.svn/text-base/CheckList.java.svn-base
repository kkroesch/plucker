/*
 * Created on 22.11.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.widget;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class CheckList extends JList {

  public CheckList(Collection items) {
    super(wrap(items));

    setCellRenderer(new CheckRenderer());
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int index = locationToIndex(e.getPoint());
        Checkable item = (Checkable) getModel().getElementAt(index);
        item.setChecked(!item.checked());
        Rectangle rect = getCellBounds(index, index);
        repaint(rect);
      }
    });    
  }
  
  public Set checkedItems() {
    Set items = new HashSet();
    for (int i = 0; i < getModel().getSize(); i++) {
      Checkable item = (Checkable) getModel().getElementAt(i);
      if (item.checked()) items.add(item.value());
    }
    return items;
  }
  
  public void checkedItems(Set items) {
    for (int i = 0; i < getModel().getSize(); i++) {
      Checkable c = (Checkable) getModel().getElementAt(i);
      if (items.contains(c.value())) c.setChecked(true);
    }
  }
  
  private static class CheckRenderer extends JCheckBox implements ListCellRenderer {
    public CheckRenderer() {
      setBackground(UIManager.getColor("List.textBackground"));
      setForeground(UIManager.getColor("List.textForeground"));
    }

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean hasFocus) {
      setEnabled(list.isEnabled());
      setSelected(((Checkable) value).checked());
      setFont(list.getFont());
      setText(((Checkable) value).value().toString());
      return this;
    }
  }

  private static Checkable[] wrap(Collection elements) {
    Checkable[] items = new Checkable[elements.size()];
    int index = 0;
    for (Object item : elements) {
      items[index++] = new Checkable(item);
    }

    return items;
  }

  /*
   * Wrapper class around 'checkable' items.
   */
  private static class Checkable {
    private boolean checked = false;

    private Object value;

    public Checkable(Object value) {
      this.value = value;
    }

    public boolean checked() {
      return checked;
    }

    public void setChecked(boolean checked) {
      this.checked = checked;
    }

    public Object value() {
      return value;
    }
  }
}
