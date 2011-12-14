package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import de.kroesch.util.GuiUtils;


public class CancelDialog extends AbstractAction {

  private JDialog dialog;
  
  public CancelDialog(JDialog dialog) {
      this.dialog = dialog;
      putValue(Action.NAME, "Cancel");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("delete.gif"));
    }

  public void actionPerformed(ActionEvent event) {
    dialog.dispose();
  }

}
