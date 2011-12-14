package com.pluralis.plucker.gui.widget;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXHyperlink;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.dialog.Editor;
import com.pluralis.plucker.gui.dialog.ItemSelector;

import de.kroesch.util.GuiUtils;


public class ListEditor extends JPanel {

  protected JList list;
  
  protected Editor editor;
  
  private EditorListModel listModel;
  
  public ListEditor(List model, Application application, Dialog parent) {
    this(model, new ItemSelector(new EditorListModel(model), application, parent));
  }
  
  public ListEditor(List model, Editor editor) {
    super(new BorderLayout(6,0), true);
    listModel = new EditorListModel(model);
    this.list = new JList(listModel);
    this.editor = editor;
    
    list.setCellRenderer(new NamedItemRenderer());

    add(new JScrollPane(list), BorderLayout.CENTER);
    add(createButtonPanel(), BorderLayout.NORTH);
  }

  private JComponent createButtonPanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.add(new JXHyperlink(new AddAction()));
    panel.add(new JXHyperlink(new RemoveAction()));
    panel.add(new JXHyperlink(new EditAction()));
    
    return panel;
  }
  
  private class AddAction extends AbstractAction {

    public AddAction() {
      putValue(Action.NAME, "Add...");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("addtask.gif"));
    }

    public void actionPerformed(ActionEvent e) {
      editor.openDialog(true);
    }
  }

  private class EditAction extends AbstractAction {

    public EditAction() {
      putValue(Action.NAME, "Edit");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("write_obj.gif"));
    }

    public void actionPerformed(ActionEvent e) {
      editor.openDialog(false);
    }
  }
  
  private class RemoveAction extends AbstractAction {

    public RemoveAction() {
      putValue(Action.NAME, "Remove");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("delete.gif"));

    }

    public void actionPerformed(ActionEvent e) {
      list.remove(list.getSelectedIndex());
      list.repaint();
    }
  }
}
