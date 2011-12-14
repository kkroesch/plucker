package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import com.pluralis.plucker.gui.dialog.Editor;

public class CreateDialog extends AbstractAction {

  private JDialog dialog;

  private Editor editor;

  public CreateDialog(JDialog dialog, Editor editor) {
    this.dialog = dialog;
    this.editor = editor;
    putValue(Action.NAME, "Create");
  }

  public void actionPerformed(ActionEvent event) {
    dialog.dispose();
    editor.add();
  }
}
