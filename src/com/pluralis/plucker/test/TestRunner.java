package com.pluralis.plucker.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.ComponentCreator;
import com.pluralis.plucker.model.TestCase;
import com.pluralis.plucker.model.UseCase;
import com.pluralis.plucker.model.TestCase.Result;

import de.kroesch.util.GuiUtils;


/**
 * A Swing based user interface to run tests.
 */
public class TestRunner implements ComponentCreator {
  
  private Logger log = Logger.getLogger("TestRunner");

  private JProgressBar progress;
  
  private DefaultListModel testSuites;
  
  private Application application;
  
  public TestRunner(Application application) {
    this.application = application;
  }

  public JComponent create() {
    JPanel panel = new JPanel(new BorderLayout(12, 12));
    panel.setBorder(new EmptyBorder(6,6,6,6));
    
    panel.add(createProgressPanel(), BorderLayout.NORTH);
    
    panel.add(new JScrollPane(createTestList()), BorderLayout.CENTER);

    panel.add(createLegend(), BorderLayout.SOUTH);
    
    return panel;
  }
  
  private JComponent createProgressPanel() {
    JPanel panel = new JPanel(new BorderLayout(12, 12));

    JLabel status = new JLabel("Project status: 100 of 12345 UCP (~10%)");
    status.setFont(new Font("Helvetica", Font.BOLD, 16));
    panel.add(status, BorderLayout.NORTH);

    progress = new JProgressBar(0, 100);
    progress.setBackground(Color.gray);
    progress.setForeground(Color.green);
    progress.setValue(0);
    progress.setStringPainted(true);
    panel.add(progress, BorderLayout.CENTER);
    
    Action runAction = new AbstractAction() {
      {putValue(Action.NAME, "Run");}
      public void actionPerformed(ActionEvent e) {
        new Thread(new RunTests(progress)).start();
      }
    };
    panel.add(new JButton(runAction), BorderLayout.EAST);

    return panel;
  }
  
  private JComponent createLegend() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 6, 6));

    panel.add(new JRadioButton("Not run", GuiUtils.readImageIcon("check.gif")));
    panel.add(new JRadioButton("Successful", GuiUtils.readImageIcon("check_selected.gif")));
    panel.add(new JRadioButton("Failed", GuiUtils.readImageIcon("failed.gif")));
    
    return panel;
  }
  
  private JList createTestList() {
    JList list = new JList();

    testSuites = new DefaultListModel();
    list.setModel(testSuites);
    for (UseCase useCase : application.getProject().getUsecase()) {
      testSuites.addElement(useCase.getTestCase());
    }
    
    list.setCellRenderer(new TestCellRenderer());
    
    return list;
  }
  
  private static class RunTests implements Runnable {
    private JProgressBar progressBar;
    
    public RunTests(JProgressBar progressBar) {
      this.progressBar = progressBar;
    }

    public void run() {
      // FIXME Only for demo.
      while (progressBar.getValue() < progressBar.getMaximum()) {
        progressBar.setValue(progressBar.getValue() + 1);
        try { Thread.sleep(500); } catch (InterruptedException e) { /* ignore */ }
      }
    }
  }
  
  public static class TestCellRenderer extends DefaultListCellRenderer implements ListCellRenderer {
    private static final Icon notRunIcon = GuiUtils.readImageIcon("check.gif"); 
    private static final Icon successIcon = GuiUtils.readImageIcon("check_selected.gif"); 
    private static final Icon failedIcon = GuiUtils.readImageIcon("failed.gif");
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      
      TestCase tc = (TestCase) value;
      Result result = Result.valueOf(tc.getResult());
      
      comp.setText(tc.getName());
      
      switch (result) {
      case FAILED:
        comp.setIcon(failedIcon);
        break;
      case SUCCESS:
        comp.setIcon(successIcon);
        break;
      default:
        comp.setIcon(notRunIcon);
        break;
      }
      
      return comp;
    }
  }
}