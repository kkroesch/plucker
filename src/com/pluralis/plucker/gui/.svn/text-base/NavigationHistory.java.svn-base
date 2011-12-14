package com.pluralis.plucker.gui;

import java.awt.event.ActionEvent;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.kroesch.util.GuiUtils;

/**
 * Allows forward and backward navigation in application topics.
 * 
 * @author karsten.kroesch
 *
 */
public class NavigationHistory {

  private Stack<ComponentCreator> history = new Stack<ComponentCreator>();
  private Stack<ComponentCreator> future = new Stack<ComponentCreator>();
  
  private Application application;
  
  public final Action forward = new ForwardAction();
  public final Action backward = new BackwardAction();

  public NavigationHistory(Application application) {
    this.application = application;
  }
  
  public void forward() {
    history.push(future.pop());
    application.setTopic(history.peek(), "");
  }
  
  public void backward() {
    future.push(history.pop());
    application.setTopic(history.peek(), "");
  }
  
  public void save(ComponentCreator comp) {
    history.push(comp);
  }

  private class ForwardAction extends AbstractAction {
    public ForwardAction() {
      putValue(Action.NAME, "Next");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("forward.gif"));
    }
    public void actionPerformed(ActionEvent e) {
      forward();
    }
  }
  
  private class BackwardAction extends AbstractAction {
    public BackwardAction() {
      putValue(Action.NAME, "Previous");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("backward.gif"));
    }
    public void actionPerformed(ActionEvent e) {
      backward();
    }
  }
}
