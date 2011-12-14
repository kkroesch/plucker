/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.UseCaseEditor;

import de.kroesch.util.GuiUtils;


public class AddUseCase extends AbstractAction {
  
  private Application application;
  
  
  public AddUseCase(Application application) {
    this.application = application;

    putValue(Action.NAME, "Add Use Case");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("open.gif"));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift U"));
  }

  public void actionPerformed(ActionEvent event) {
    UseCaseEditor editor = new UseCaseEditor(application);
    editor.openDialog(true);
  }
}