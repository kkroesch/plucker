package com.pluralis.plucker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class PropertyTableModel extends AbstractTableModel {

  private Map properties;
  
  private List<String> keys;
  
  public PropertyTableModel(Map properties) {
    this.properties = properties;
    keys = new ArrayList<String>(properties.size());
    keys.addAll(properties.keySet());
  }
  
  public int getRowCount() {
    return properties.size();
  }

  public int getColumnCount() {
    return 2;
  }

  public Object getValueAt(int row, int column) {
    if (column == 0) return keys.get(row);
    if (column == 1) return properties.get(keys.get(row));
    
    throw new ArrayIndexOutOfBoundsException();
  }
  
  @Override
  public boolean isCellEditable(int row, int column) {
    return column == 1;
  }
  
  @Override
  public void setValueAt(Object value, int row, int column) {
    assert column == 1;
    properties.put(keys.get(row), value);
  }
}