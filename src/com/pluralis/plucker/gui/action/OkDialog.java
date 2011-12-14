package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import com.pluralis.plucker.gui.dialog.Editor;

public class OkDialog extends AbstractAction {

  private JDialog dialog;

  private Editor editor;

  public OkDialog(JDialog dialog, Editor editor) {
    this.dialog = dialog;
    this.editor = editor;
    putValue(Action.NAME, "OK");
  }

  public void actionPerformed(ActionEvent event) {
    dialog.dispose();
    editor.save();
  }

}
