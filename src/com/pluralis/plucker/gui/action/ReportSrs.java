/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.lowagie.text.DocumentException;
import com.pluralis.plucker.export.PdfExport;
import com.pluralis.plucker.gui.Application;

import de.kroesch.util.GuiUtils;


public class ReportSrs extends AbstractAction {

  private Logger log = Logger.getLogger("PluginRegistry");

  private String fileName = "SRS.rtf";
  
  private Application application;
  
  
  public ReportSrs(Application application) {
    this.application = application;

    putValue(Action.NAME, "Export Specification");
    putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("copy.gif"));
  }

  public void actionPerformed(ActionEvent event) {
    Cursor cursor = application.rootWindow().getContentPane().getCursor(); 
    application.rootWindow().getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
    
    JFileChooser chooser = new JFileChooser();

    FileFilter filter = new SpecFileFilter();
    chooser.setCurrentDirectory(application.options().getLastProjectLocation());
    chooser.setFileFilter(filter);

    try {
      int returnVal = chooser.showSaveDialog(application.rootWindow());
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        fileName = chooser.getSelectedFile().getAbsolutePath();
        if (! fileName.endsWith(".rtf")) fileName += ".rtf";
      } else {
        return;
      }
    
      PdfExport exporter = new PdfExport(application.getProject());
      exporter.export(fileName);
      
      log.fine("Successfully exported to: " + fileName);
    } catch (IOException e) {
      log.severe("Exporting to: " + fileName);
      throw new RuntimeException(e);
    } catch (DocumentException e) {
      log.severe("Exporting to: " + fileName);
      throw new RuntimeException(e);
    } finally {
      application.rootWindow().getContentPane().setCursor(cursor);
    }
  }
  
  public static class SpecFileFilter extends FileFilter {
    @Override
    public String getDescription() {
      return "RTF Files";
    }
    @Override
    public boolean accept(File file) {
      return (file.isDirectory() || file.getName().endsWith(".rtf"));
    }
  }
}
