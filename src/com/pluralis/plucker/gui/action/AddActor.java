/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.ActorEditor;

import de.kroesch.util.GuiUtils;


public class AddActor extends AbstractAction {
  private Application application;
  
  public AddActor(Application application) {
    this.application = application;
    putValue(Action.NAME, "Add Actor");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("worker.png"));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift A"));
  }

  public void actionPerformed(ActionEvent event) {
    ActorEditor editor = new ActorEditor(application);
    editor.openDialog(true);
  }
}