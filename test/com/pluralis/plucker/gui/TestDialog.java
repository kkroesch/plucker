package com.pluralis.plucker.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.kroesch.util.GuiUtils;

public class TestDialog extends JFrame {

	public TestDialog(ComponentCreator creator) {
    this(creator.create());
	}
  
  public TestDialog(JComponent comp) {
    getContentPane().add(comp, BorderLayout.CENTER);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //GuiUtils.centerOnScreen(this);
    pack();
    setVisible(true);
  }
}
