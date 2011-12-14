package com.pluralis.plucker.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

public class FlagDependency extends Observable {

  public JCheckBox registerCheckBox(JCheckBox check) {
    check.addActionListener(new CheckListener());
    return check;
  }
  
  public JRadioButton registerRadioButton(JRadioButton radio) {
    radio.addChangeListener(new RadioListener(radio));
    return radio;
  }

  public JTextComponent registerText(JTextComponent text) {
    text.addCaretListener(new TextChangeListener(text));
    return text;
  }
  
  public JComponent registerComponent(JComponent component) {
    return registerComponent(component, true);
  }
  
  public JComponent registerComponent(JComponent component, boolean enabled) {
    addObserver(new ComponentObserver(component));
    component.setEnabled(enabled);
    return component;
  }

  public static class ComponentObserver implements Observer {
    private JComponent component;
    
    public ComponentObserver(JComponent component) {
      this.component = component;
    }
    public void update(Observable o, Object arg) {
      try {
        component.setEnabled(((Boolean)arg).booleanValue());
      } catch (Exception e) {
        // Wrong arguments? Toggle!
        component.setEnabled(! component.isEnabled());
      }
    }
  }

  public class RadioListener implements ChangeListener {
    private JRadioButton radio;
    public RadioListener(JRadioButton radio) {
      this.radio = radio;
    }

    public void stateChanged(ChangeEvent e) {
      FlagDependency.this.setChanged();
      FlagDependency.this.notifyObservers(Boolean.valueOf(radio.isSelected()));
    }
  }

  public class CheckListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      FlagDependency.this.setChanged();
      FlagDependency.this.notifyObservers();
    }
  }
  
  public class TextChangeListener implements CaretListener {
    private JTextComponent text;
    public TextChangeListener(JTextComponent text) {
      this.text = text;
    }

    public void caretUpdate(CaretEvent e) {
      FlagDependency.this.setChanged();
      FlagDependency.this.notifyObservers(Boolean.valueOf(
          text.getText() != null && text.getText().trim().length() > 0));
    }
  }
}
