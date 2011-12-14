package com.pluralis.plucker.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/** 
 * Contains the general layout.
 * 
 * @author Karsten
 */
public class ContainerPanel {

  private SimpleInternalFrame mainRightPanel;
  
  private Application application;
  
  public ContainerPanel(Application application) {
    this.application = application;
    mainRightPanel = buildMainRightPanel();
  }

  private void setTopicComponent(JComponent content, String text) {
    mainRightPanel.setContent(content);
    mainRightPanel.setTitle(text);
  }
  
  public void setTopicComponent(ComponentCreator creator, String text) {
    Cursor cursor = application.rootWindow().getContentPane().getCursor(); 
    application.rootWindow().getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));

    try {
      setTopicComponent(creator.create(), text);
    } finally {
      application.rootWindow().getContentPane().setCursor(cursor);
    }
  }
  
  /**
   * Builds and returns the panel.
   */
  public JComponent build() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new NavigationPanel(application).create(), BorderLayout.WEST);
    panel.add(mainRightPanel, BorderLayout.CENTER);
    return panel;
  }

  
  

  /**
   * Builds and returns a tabbed pane with the no-content-border enabled.
   */
  private SimpleInternalFrame buildMainRightPanel() {
    mainRightPanel = new SimpleInternalFrame("Welcome");
    mainRightPanel.setPreferredSize(new Dimension(300, 100));
    mainRightPanel.setContent(new WelcomeScreen(application).create());
    return mainRightPanel;
  }
}