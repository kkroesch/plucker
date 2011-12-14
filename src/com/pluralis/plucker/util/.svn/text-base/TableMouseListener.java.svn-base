/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.swingx.JXTable;

public abstract class TableMouseListener extends MouseAdapter {

  protected JXTable table;
  
  public TableMouseListener(JXTable table) {
    this.table = table;
  }

  public void mouseClicked(MouseEvent event) {
    if (event.isPopupTrigger()) {
      // FIXME
    }
    if (event.getClickCount() > 1) doubleClick();
  }
  
  public abstract void doubleClick();
  
  public abstract void popupMenu();

}