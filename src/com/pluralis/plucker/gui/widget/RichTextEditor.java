package com.pluralis.plucker.gui.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

public class RichTextEditor extends JPanel {
  
  private final JEditorPane pane; 

  public RichTextEditor() {
    
    EditorKit editorKit = new HTMLEditorKit();
    pane = new JEditorPane();
    pane.setMinimumSize(new Dimension(200, 50));
    
    JToolBar toolBar = new JToolBar();
    for (Action action : editorKit.getActions()) {
      toolBar.add(action);
    }
    
    setLayout(new BorderLayout());
    add(toolBar, BorderLayout.NORTH);
    add(new JScrollPane(pane), BorderLayout.CENTER);
  }

}
