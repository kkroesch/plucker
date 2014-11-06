/*
 * Created on 26.10.2005 by Karsten $Id$
 */
package de.kroesch.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;


/**
 * Provides several utilities for developing Swing applications.
 * 
 * @author karsten
 *
 */
public class GuiUtils {

  private static Cursor origCursor;
  private static JFrame actualWindow;
  
  private static Logger log = Logger.getLogger("GuiUtils");
  
  /**
   * Centers window in the middle of the screen.
   */
  public static void centerOnScreen(Window window) {
    Dimension screenSize =
      Toolkit.getDefaultToolkit().getScreenSize();
    Dimension labelSize = window.getPreferredSize();
    window.setLocation(screenSize.width/2 - (labelSize.width/2),
                screenSize.height/2 - (labelSize.height/2));    
  }
  
  /**
   * Centers the window on the middle of the parent window; for example modal dialogs.
   */
  public static void center(Window window, Window parent) {
    int x = parent.getX();
    int y = parent.getY();
    Dimension screenSize = parent.getSize();
    Dimension labelSize = window.getPreferredSize();
    window.setLocation(x + screenSize.width/2 - (labelSize.width/2),
        y + screenSize.height/2 - (labelSize.height/2));    
  }

  /**
   * Sets the system-dependent waiting cursor to display long-running operations.
   */
  public static void setWaitCursor(JFrame window) {
    actualWindow = window;
    origCursor = window.getCursor();
    
    window.setCursor(new Cursor(Cursor.WAIT_CURSOR));
  }
  
  /**
   * Reset cursor to normal pointer.
   */
  public static void resetCursor() {
    actualWindow.setCursor(origCursor);
  }

  /**
   * Reads an image from the resource folder.
   */
  public static ImageIcon readImageIcon(String filename) {
    URL url;
    try {
      url = GuiUtils.class.getClassLoader().getResource("images/" + filename);
      log.info("Requesting image " + url);
    } catch (Exception e) {
      log.severe("Cannot retrieve image " + filename);
      throw new RuntimeException(e);
    }
    return new ImageIcon(url);
  }

  /**
   * Convenience method to retrieve an arbitrary ressource.
   */
  public static URL resourceFile(String relativePath) {
    try {
      log.info("Requesting resource " + relativePath);
      return GuiUtils.class.getClassLoader().getResource(relativePath);
    } catch (RuntimeException e) {
      log.severe("Cannot retrieve resource " + relativePath);
      throw new RuntimeException(e);
    }
  }

  /**
   * Sets a JTable's column widths in pixels.
   */
  public static void columnSize(JTable table, int[] widths) {
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    for (int colIndex = 0; colIndex < widths.length; colIndex++) {
      TableColumn col = table.getColumnModel().getColumn(colIndex);
      col.setPreferredWidth(widths[colIndex]);
    }
  }
  
  /**
   * Display a help label. Requires a "help" icon.
   */
  public static JComponent createHintLabel(String message) {
    JLabel hint = new JLabel(message);
    hint.setOpaque(true);
    hint.setBackground(new Color(0xbf, 0xcf, 0xff));
    hint.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(0x00, 0x24, 0x8f)), new EmptyBorder(2,2,2,2)));
    hint.setIcon(readImageIcon("help.gif"));
    
    return hint;
  }
}