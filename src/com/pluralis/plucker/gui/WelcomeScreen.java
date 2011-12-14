package com.pluralis.plucker.gui;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTree;

import com.jgoodies.binding.PresentationModel;
import com.pluralis.plucker.gui.action.AddActor;
import com.pluralis.plucker.gui.action.AddRequirement;
import com.pluralis.plucker.gui.action.AddUseCase;
import com.pluralis.plucker.gui.action.SetProjectOptions;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.Project;
import com.pluralis.plucker.model.ProjectTreeModel;


public class WelcomeScreen implements ComponentCreator {

  protected Application application;
  
  protected PresentationModel adapter;
  
  public static void main(String[] args) {
    Application app = new TestApplication(); 
    app.setTopic(new WelcomeScreen(app), "Document Management");
  }
  
  public WelcomeScreen(Application application) {
    this.application = application;
    adapter = new PresentationModel(application.getProject());
  }

  public JComponent create() {
    JXTaskPaneContainer container = new JXTaskPaneContainer();
    
    // Vision and Scope Phase
    JXTaskPane vascPane = new JXTaskPane();
    vascPane.setTitle("Vision ad Scope Phase");
    vascPane.setSpecial(true);
    JLabel title = new JLabel(application.getProject().getName());
    title.setFont(new Font("Verdana", Font.BOLD, 14));
    vascPane.add(title);
    
    JTextArea desc = new JTextArea(application.getProject().getDescription());
    desc.setEditable(false);
    desc.setBorder(new EmptyBorder(4,21,4,4));
    vascPane.add(desc);
    
    JComponent fl = new FileList(((Project)new PresentationModel(application.getProject()).getBean()).getRelatedDocuments(), application).create();
    vascPane.add(fl);
    vascPane.add(new JXHyperlink(new SetProjectOptions(application)));
    
    container.add(vascPane);
    
    // Requirements
    JXTaskPane reqPane = new JXTaskPane();
    reqPane.setTitle("Requirements Phase");
    reqPane.setSpecial(true);
    reqPane.add(new JXHyperlink(new AddRequirement(application)));
    reqPane.add(new JScrollPane(new JList(application.getProject().getRequirement().toArray())));
    container.add(reqPane);
    
    // Actors
    JXTaskPane actorPane = new JXTaskPane();
    actorPane.setTitle("Defining Actors");
    actorPane.setSpecial(true);
    actorPane.add(new JXHyperlink(new AddActor(application)));
    container.add(actorPane);
    
    // Use Cases
    JXTaskPane ucPane = new JXTaskPane();
    ucPane.setTitle("Defining Use Cases");
    ucPane.setSpecial(true);
    ucPane.add(new JXHyperlink(new AddUseCase(application)));
    container.add(ucPane);
    
    
    JComponent comp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    JTree pTree = new JXTree(new ProjectTreeModel(application.getProject()));
    pTree.setBorder(new EmptyBorder(2,2,2,2));
    comp.add(new JScrollPane(pTree), JSplitPane.LEFT);
    comp.add(new JScrollPane(container), JSplitPane.RIGHT);

    return comp;
  }
  
}
