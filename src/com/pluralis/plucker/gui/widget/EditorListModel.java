/**
 * 
 */
package com.pluralis.plucker.gui.widget;

import java.util.List;

import javax.swing.AbstractListModel;

public class EditorListModel extends AbstractListModel {

  private List model;
  
  public EditorListModel(List model) {
    this.model = model;
  }
  
  public void add(Object value) {
    model.add(value);
    fireContentsChanged(value, 0, model.size());
  }
  
  public void add(List values) {
    model.addAll(values);
    fireContentsChanged(values, 0, model.size());
  }

  public void remove(Object value) {
    model.remove(value);
    fireContentsChanged(value, 0, model.size());
  }

  public Object getElementAt(int index) {
    return model.get(index);
  }

  public int getSize() {
    return model.size();
  }
}