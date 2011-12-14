package com.pluralis.plucker.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.action.DialogDisposeAction;
import com.pluralis.plucker.gui.widget.EditorListModel;
import com.pluralis.plucker.gui.widget.NamedItemRenderer;
import com.pluralis.plucker.model.NamedEntry;

import de.kroesch.util.GuiUtils;


public class ItemSelector extends JDialog implements Editor {

  private Vector<NamedEntry> selectableItems = new Vector<NamedEntry>();

  private EditorListModel listModel;
  private JList selectionList;
  
  public ItemSelector(EditorListModel listModel, Application application, Dialog parent) {
    super(parent, "Select Items", false);
    
    this.listModel = listModel;
    
    selectableItems.addAll(application.getProject().getRequirement());
    
    Box box = Box.createVerticalBox();
    box.setBorder(new TitledBorder("Select Participants"));
    
    ListCellRenderer lcr = new NamedItemRenderer();
    
    selectionList = new JList(selectableItems);
    selectionList.setCellRenderer(lcr);

    JTextField searchField = new JTextField();
    //searchField.addCaretListener(new SearchStarter(searchField, selectionList, selectableItems));
    searchField.requestFocus();
    box.add(searchField);
    
    box.add(new JScrollPane(selectionList));
    getContentPane().add(box, BorderLayout.CENTER);
    
    getContentPane().add(createButtonPanel(), BorderLayout.SOUTH);
    application.centerDialog(this);
    pack();
  }
  
  private Component createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(new JButton(new AddParticipants(selectionList, listModel)));
    buttonPanel.add(new JButton(new DialogDisposeAction(this)));
    return buttonPanel;
  }

  public void add() {
    // NOT NEDDED
  }

  public void cancel() {
    // NOT NEDDED
  }

  public void openDialog(boolean create) {
    setVisible(true);
  }

  public void save() {
    // NOT NEDDED saved automatically
  }
  
  private static class AddParticipants extends AbstractAction {
    private JList source;
    private EditorListModel listModel;
    
    public AddParticipants(JList source, EditorListModel listModel) {
      this.source = source;
      this.listModel = listModel;
      putValue(Action.NAME, "Add");
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("addtask.gif"));
    }

    public void actionPerformed(ActionEvent e) {
      listModel.add(Arrays.asList(source.getSelectedValues()));
    }
  }
  
  

  public Vector<NamedEntry> getSelectableItems() {
    return selectableItems;
  }

  public void setSelectableItems(Vector<NamedEntry> selectableItems) {
    this.selectableItems = selectableItems;
  }
}
