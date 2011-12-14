/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.TestcaseEditor;

import de.kroesch.util.GuiUtils;


public class AddTestCase extends AbstractAction {
  private Application application;
  
  public AddTestCase(Application application) {
    this.application = application;

    putValue(Action.NAME, "Add Test Case");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("passed.gif"));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift T"));
  }
  
  public void actionPerformed(ActionEvent event) {
    new TestcaseEditor(application).openDialog(true);
  }
}