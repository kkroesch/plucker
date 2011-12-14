package com.pluralis.plucker.gui;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXHyperlink;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;
import com.pluralis.plucker.gui.action.AddActor;
import com.pluralis.plucker.gui.action.AddChangeRequest;
import com.pluralis.plucker.gui.action.AddRequirement;
import com.pluralis.plucker.gui.action.AddTask;
import com.pluralis.plucker.gui.action.AddTestCase;
import com.pluralis.plucker.gui.action.AddUseCase;
import com.pluralis.plucker.gui.action.ReportSrs;

public class NavigationPanel implements ComponentCreator {

  private Application application;
  
  public NavigationPanel(Application application) {
    super();
    this.application = application;
  }

  public JComponent create() {
    FormLayout layout = new FormLayout("default", "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    
    SimpleInternalFrame addComponents = new SimpleInternalFrame("Create Components");
    Box box = Box.createVerticalBox();
    box.setBorder(new EmptyBorder(2,2,2,2));
    box.add(decorate(new JXHyperlink(new AddActor(application))));
    box.add(decorate(new JXHyperlink(new AddUseCase(application))));
    box.add(decorate(new JXHyperlink(new AddRequirement(application))));
    box.add(decorate(new JXHyperlink(new AddTestCase(application))));
    box.add(decorate(new JXHyperlink(new AddTask(application))));
    box.add(decorate(new JXHyperlink(new AddChangeRequest(application))));
    addComponents.add(box);
    builder.append(addComponents);

    SimpleInternalFrame viewReports = new SimpleInternalFrame("Reports");
    Box box2 = Box.createVerticalBox();
    box2.setBorder(new EmptyBorder(2,2,2,2));
    box2.add(decorate(new JXHyperlink(new ReportSrs(application))));
    viewReports.add(box2);
    builder.append(viewReports);
    
    JPanel panel = builder.getPanel();
    panel.setBorder(new EmptyBorder(0, 0, 0, 10));
    
    return panel;
  }
  
  private JComponent decorate(JComponent panel) {
    panel.setBorder(new EmptyBorder(2,6,2,6));
    return panel;
  }
}
