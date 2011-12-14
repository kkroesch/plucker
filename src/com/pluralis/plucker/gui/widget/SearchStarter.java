package com.pluralis.plucker.gui.widget;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * Reduces number of list items according to the input in the text box.
 * 
 * Basic usage:
 * <code>
 *   JList list;
 *   JTextField field;
 *   Collection base; // The items for list
 *   
 *   ...
 *   field.addCaretListener(new SearchStarter(field, list, base);
 *   ...
 * </code>
 * 
 * @author karsten.kroesch
 *
 */
public class SearchStarter implements CaretListener {
  
  private JTextField searchField;
  
  private JList listView;
  
  private Vector<String> base;
  
  public SearchStarter(JTextField field, JList listView, Collection base) {
    searchField = field;
    this.listView = listView;

    this.base = new Vector<String>(base.size());
    this.base.addAll(base);
    
    // Necessary for binary search
    Collections.sort(this.base, new CaseInsensitiveComparator());
  }
  
  public void caretUpdate(CaretEvent event) {
    String key = searchField.getText();
    
    // No input means "show all entries"
    if (key == null || "".equals(key.trim())) {
      listView.setListData(base);
      listView.getParent().repaint();
      return;
    }
    
    key = key.trim();
    int index = Collections.binarySearch(base, key, new CaseInsensitiveComparator());
    
    Vector<String> hits = new Vector<String>();
    if (index < 0) index = -index - 1;
    if (index >= base.size()) index = 0;
    
    while (base.get(index).toLowerCase().startsWith(key.toLowerCase())) {
      hits.add(base.get(index));
      index ++;
    }
    
    listView.setListData(hits);
    listView.getParent().repaint();
  }
  
  public static class CaseInsensitiveComparator implements Comparator<String> {
    public int compare(String one, String another) {
      return one.compareToIgnoreCase(another);
    }
  }
}