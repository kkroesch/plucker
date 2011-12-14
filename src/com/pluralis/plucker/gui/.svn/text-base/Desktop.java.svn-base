/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.pluralis.plucker.gui.action.AddAppointment;
import com.pluralis.plucker.gui.action.AddBookmark;
import com.pluralis.plucker.gui.action.AddTask;
import com.pluralis.plucker.gui.action.SendMessage;
import com.pluralis.plucker.gui.widget.Separator;

import de.kroesch.util.GuiUtils;



public class Desktop implements ComponentCreator {

  public Box messagePanel;
  public Box calendarPanel;
  public Box taskPanel;
  public Box favouritesPanel;
  
  private Application application;
  
  public static Color BACKGROUND_COLOR = Color.WHITE;

  public Desktop(Application application) {
    this.application = application;
  }

  public JComponent create() {
    Box comp = Box.createVerticalBox();

    comp.setBackground(BACKGROUND_COLOR);
    
    comp.add(new Separator("New Messages", GuiUtils.readImageIcon("boxin.gif")));
    comp.add(new Message("From JP: Please contact Peter Sharnell"));
    
    comp.add(new Bookmark(new SendMessage(application), "New Message", GuiUtils.readImageIcon("addtask.gif")));

    comp.add(new Separator("Current Dates & Events", GuiUtils.readImageIcon("calender.gif")));
    comp.add(new Message("24/12/2005: Christmas w/ family"));
    comp.add(new Bookmark(new AddAppointment(application), "New Event", GuiUtils.readImageIcon("addtask.gif")));
    
    comp.add(new Separator("Current Tasks", GuiUtils.readImageIcon("chklst.gif")));
    comp.add(new Todo("Fix all bugs"));
    comp.add(new Bookmark(new AddTask(application), "New Task", GuiUtils.readImageIcon("addtask.gif")));
    
    comp.add(new Separator("My Favourites", GuiUtils.readImageIcon("bookmark.gif")));
    comp.add(new Bookmark(null, "Sales Report", GuiUtils.readImageIcon("report.gif")));
    comp.add(new Bookmark(new AddBookmark(), "New Bookmark", GuiUtils.readImageIcon("addtask.gif")));

    return new JScrollPane(comp);
  }
  
  private static class Message extends JPanel {
    public Message(String text) {
      text = "<html><u>" + text + "</u></html>";
      JLabel label = new JLabel(text, GuiUtils.readImageIcon("bullet.gif"), SwingConstants.LEFT);
      label.setForeground(Color.blue);
      label.setCursor(new Cursor(Cursor.HAND_CURSOR));
      setLayout(new FlowLayout(FlowLayout.LEFT));
      add(label);
      
      setBackground(BACKGROUND_COLOR);
    }
  }

  private static class Todo extends JPanel {
    public Todo(String text) {
      text = "<html><u>" + text + "</u></html>";
      JLabel label = new JLabel(text);
      label.setForeground(Color.blue);
      label.setCursor(new Cursor(Cursor.HAND_CURSOR));
      setLayout(new FlowLayout(FlowLayout.LEFT));
      add(new JCheckBox());
      add(label);
      
      setBackground(BACKGROUND_COLOR);
    }
  }

  private static class Bookmark extends JPanel {
    public Bookmark(Action action) {
      this(action, (String)action.getValue(Action.NAME), 
                   (Icon)action.getValue(Action.SMALL_ICON));
    }
    
    public Bookmark(Action action, String text, Icon icon) {
      text = "<html><u>" + text + "</u></html>";
      JLabel label = new JLabel(text, icon, SwingConstants.LEFT);
      label.setForeground(Color.blue);
      label.setBackground(Color.white);
      label.setCursor(new Cursor(Cursor.HAND_CURSOR));
      label.addMouseListener(new LinkListener(action));
      setLayout(new FlowLayout(FlowLayout.LEFT));
      add(label);
      
      setBackground(BACKGROUND_COLOR);
    }
  }
  
  private static class LinkListener implements MouseListener {
    
    private Action action;
    
    public LinkListener(Action action) {
      this.action = action;
    }

    public void mouseClicked(MouseEvent event) {
      action.actionPerformed(new ActionEvent(event.getSource(), 1, "add"));
    }

    public void mouseEntered(MouseEvent event) {/* EMPTY */}
    public void mouseExited(MouseEvent event) {/* EMPTY */}
    public void mousePressed(MouseEvent event) {/* EMPTY */}
    public void mouseReleased(MouseEvent event) {/* EMPTY */}
  }
}
