package com.pluralis.plucker.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;

/**
 * Deferres the focusing of a certain component to the opening of the window.
 * 
 * @author karsten.kroesch
 *
 */
public class FocusRequester extends WindowAdapter {
  
  private JComponent comp;
  
  public FocusRequester(JComponent comp) {
    this.comp = comp;
  }

  @Override
  public void windowOpened(WindowEvent e) {
    comp.requestFocus();
  }

}
