/*
 * Created on 20.11.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import de.kroesch.util.GuiUtils;


public class DialogDisposeAction extends AbstractAction {

  private JDialog dialog;
  
  public DialogDisposeAction(JDialog dialog) {
    this.dialog = dialog;
    putValue(Action.NAME, "Finish");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("passed.gif"));
  }

  public void actionPerformed(ActionEvent e) {
    dialog.dispose();
  }

}
