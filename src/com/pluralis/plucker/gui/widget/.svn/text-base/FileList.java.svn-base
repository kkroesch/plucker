package com.pluralis.plucker.gui.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXHyperlink;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;

import de.kroesch.util.GuiUtils;


public class FileList implements ComponentCreator {

  private Logger log = Logger.getLogger(FileList.class);
  
  private Map<String, String> extensions = new HashMap<String, String>();
  
  protected final List<String> files;

  protected JList listView;
  
  protected Application application;
  
  public FileList(List<String> files, Application application) {
    this.files = files;
    this.application = application;
    initializeExtensions();
  }
  
  protected void doubleClick() {
    File file = new File(listView.getSelectedValue().toString());
    log.info("Opening " + file);
    try {
      // FIXME 
      Process proc = Runtime.getRuntime().exec("cmd.exe /c \"" + file.getAbsolutePath() + "\"");
    } catch (IOException ex) {
      log.error("Cannot open " + file, ex);
      throw new RuntimeException(ex);
    }
  }

  protected void initializeExtensions() {
    extensions.put("pdf", "files/pdf.gif");
    extensions.put("txt", "files/text.gif");
    extensions.put("jar", "files/zip.gif");
    extensions.put("zip", "files/zip.gif");
    extensions.put("doc", "files/ms-word.gif");
    extensions.put("xls", "files/excel.gif");
  }
  
  public JComponent create() {
    listView = new JList(new EditorListModel(files));
    listView.setSize(40, 63);
    
    listView.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() > 1) doubleClick();
      }
    });

    listView.setCellRenderer(new DefaultListCellRenderer(){
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Strip extension
        String[] fileparts = value.toString().split("\\.");
        String extension = fileparts.length == 0 ? "" : fileparts[fileparts.length - 1];

        renderer.setIcon(iconFor(extension));
        
        return renderer;
      }
    });
    
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JScrollPane(listView), BorderLayout.CENTER);
    panel.add(createButtonPanel(), BorderLayout.NORTH);
    
    return panel;
  }
    
  protected Icon iconFor(String extension) {
    Object filename = extensions.get(extension);
    if (filename == null)
      return GuiUtils.readImageIcon("files/file.gif");
    
    return GuiUtils.readImageIcon(filename.toString());
  }
  
  private JComponent createButtonPanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.add(new JXHyperlink(new AddFile()));
    panel.add(new JXHyperlink(new RemoveFile()));
    
    return panel;
  }
  
  private class AddFile extends AbstractAction {
    public AddFile() {
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("addtask.gif"));
      putValue(Action.NAME, "Add File...");
    }
    
    public void actionPerformed(ActionEvent e) {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(application.rootWindow());
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        ((EditorListModel)listView.getModel()).add(chooser.getSelectedFile().getAbsolutePath());
      }
    }
  }
  
  private class RemoveFile extends AbstractAction {
    public RemoveFile() {
      putValue(Action.SMALL_ICON, GuiUtils.readImageIcon("delete.gif"));
      putValue(Action.NAME, "Remove");
    }
    
    public void actionPerformed(ActionEvent e) {
        ((EditorListModel)listView.getModel()).remove(listView.getSelectedValue());
    }
  }
}
