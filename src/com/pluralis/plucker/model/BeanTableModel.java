/*
 * Created on 25.08.2005 by Karsten $Id$
 */
package com.pluralis.plucker.model;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

public class BeanTableModel extends AbstractTableModel implements Observer {

  protected List rows;
  
  protected PropertyDescriptor[] descriptors;
  
  protected BeanTableModel() {/*default*/}
  
  /**
   * 
   * @param rows The objects in that list must follow the JavaBean specification.
   */
  public BeanTableModel(List rows) throws Exception {
    this.rows = rows;
    initialize();
  }
  
  public BeanTableModel(PropertyDescriptor[] descriptors, List rows) {
    this.descriptors = descriptors;
    this.rows = rows;
  }

  protected void initialize() throws Exception {
    if (rows.size() < 1) return;
    
    // FALLBACK:
    descriptors = Introspector.getBeanInfo(rows.get(0).getClass()).getPropertyDescriptors();
  }

  public int getColumnCount() {
    return descriptors.length;
  }

  public int getRowCount() {
    return rows.size();
  }

  public Object getValueAt(int row, int col) {
    Object instance = rows.get(row);
    try {
      return descriptors[col].getReadMethod().invoke(instance, (Object[]) null);
    } catch (Exception e) {
      return e;
    }
  }
  
  public Object getValueAt(int row) {
    return rows.get(row);
  }
  
  public void removeValueAt(int row) {
    rows.remove(row);
    fireTableDataChanged();
  }
  
  public void addValue(Object value) {
    rows.add(value);
  }
  
  public String getColumnName(int col) {
    return descriptors[col].getDisplayName();
  }
  
  public Iterator iterator() {
    return rows.iterator();
  }
  
  public void clear() {
    rows.clear();
  }
  
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return super.isCellEditable(rowIndex, columnIndex);
  }
  
  /**
   * @return Returns the descriptors.
   */
  public PropertyDescriptor[] getDescriptors() {
    return descriptors;
  }

  public void update(Observable observable, Object value) {
    fireTableDataChanged();
  }

  public void removeValue(Object value) {
    rows.remove(value);
  }
}
