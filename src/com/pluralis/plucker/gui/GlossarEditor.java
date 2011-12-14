package com.pluralis.plucker.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXEditorPane;
import org.jdesktop.swingx.JXHyperlink;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.widget.SearchStarter;
import com.pluralis.plucker.model.GlossaryEntry;

import de.kroesch.util.GuiUtils;

public class GlossarEditor implements ComponentCreator {

  protected Map<String, GlossaryEntry> glossary;
  
  protected JTextField entry;
  
  protected JEditorPane description;
  
  protected JList list;
  
  protected DefaultListModel entryModel = new DefaultListModel();
  
  public GlossarEditor() {
    this.glossary = new HashMap<String, GlossaryEntry>();
    for (GlossaryEntry ge : GlossaryEntry.SAMPLE_ENTRIES) {
      String term = ge.getTerm();
      glossary.put(term, ge);
      entryModel.addElement(term);
    }
  }

  public GlossarEditor(Map<String, GlossaryEntry> glossary) {
    this.glossary = glossary;
    for (String term : glossary.keySet()) {
      entryModel.addElement(term);
    }
  }

  public JComponent create() {
    JPanel comp = new JPanel(new BorderLayout(12,12));
    
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 80dlu:grow, 6dlu, 30dlu, 6dlu, 35dlu", "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.setBorder(new EmptyBorder(6,6,6,6));
    
    entry = new JTextField();
    list = new JList(entryModel);

    final Action addAction = new Add();
    final Action deleteAction = new Delete(list);
    
    builder.append("Entry", entry, new JXHyperlink(addAction), new JXHyperlink(deleteAction));
    
    list.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (list.getSelectedIndex() < 0) return;

        if (e.getValueIsAdjusting()) {
          entry.setText(list.getSelectedValue().toString());
        } else {
          GlossaryEntry ge = new GlossaryEntry(entry.getText(), description.getText());
          glossary.put(ge.getTerm(), ge);
        }
        
        GlossaryEntry ge = glossary.get(list.getSelectedValue());
        entry.setText(ge.getTerm());
        description.setText(ge.getDescription());
      }
    });
    builder.append(new JScrollPane(list), 7);

    JComponent leftPanel = builder.getPanel();
    description = new JXEditorPane();
    //description.setEditorKit(new HTMLEditorKit());
    JComponent rightPanel = new JScrollPane(description);
    rightPanel.setBorder(new EmptyBorder(6,6,6,6));
    
    comp.add(leftPanel, BorderLayout.WEST);
    comp.add(rightPanel, BorderLayout.CENTER);
    return comp;
  }
  
  private class Add extends AbstractAction {
    public Add() {
      putValue(Action.NAME, "Add");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("icons/ok.gif"));
    }

    public void actionPerformed(ActionEvent e) {
      GlossaryEntry ge = new GlossaryEntry(entry.getText(), description.getText());
      glossary.put(ge.getTerm(), ge);
      if (entryModel.contains(ge.getTerm())) return;
      entryModel.addElement(ge.getTerm());
    }
  }

  private class Delete extends AbstractAction {
    private JList list;
    public Delete(JList list) {
      this.list = list;
      putValue(Action.NAME, "Delete");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("delete.gif"));
    }

    public void actionPerformed(ActionEvent e) {
      String term = list.getSelectedValue().toString();
      glossary.remove(term);
      entryModel.removeElement(term);
    }
  }

  // FIXME
  private class ToggleLookup extends AbstractAction {
    private boolean lookup = false;
    private CaretListener listener;
    public ToggleLookup() {
      putValue(Action.NAME, "Lookup");
    }
    public void actionPerformed(ActionEvent e) {
      if (lookup) {
        listener = new SearchStarter(entry, list, glossary.keySet());
        entry.addCaretListener(listener);
      } else {
        entry.removeCaretListener(listener);
      }
      lookup =! lookup;
    }
  }
}