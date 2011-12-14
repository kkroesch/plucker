/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pluralis.plucker.gui.dialog.BookmarkDialog;

public class AddBookmark extends AbstractAction {

  public void actionPerformed(ActionEvent event) {
    new BookmarkDialog().setVisible(true);
  }
}
