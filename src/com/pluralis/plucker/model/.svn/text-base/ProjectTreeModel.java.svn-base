/*
 * Created on 24.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class ProjectTreeModel implements TreeModel {

  private EventListenerList listener = new EventListenerList();

  private ProjectNode root;

  public ProjectTreeModel(Project project) {
    List<ProjectNode> pNodes = new ArrayList<ProjectNode>(5);
    pNodes.add(new ProjectNode(project.getRelatedDocuments(), "Documents"));
    pNodes.add(new ProjectNode(project.getRequirement(), "Requirements"));
    pNodes.add(new ProjectNode(project.getActor(), "Actors"));
    pNodes.add(new ProjectNode(project.getUsecase(), "Use Cases"));
    pNodes.add(new ProjectNode(project.getTestCase(), "Test Cases"));
    pNodes.add(new ProjectNode(project.getActivity(), "Activities"));
    pNodes.add(new ProjectNode(project.getChangeRequest(), "Change Requests"));

    root = new ProjectNode(pNodes, project.getName());
  }

  public Object getChild(Object parent, int index) {
    return ((ProjectNode)parent).getChildren().get(index);
  }

  public int getChildCount(Object parent) {
    return ((ProjectNode)parent).getChildren().size();
  }

  public int getIndexOfChild(Object parent, Object child) {
    return ((ProjectNode)parent).getChildren().indexOf(child);
  }

  public Object getRoot() {
    return root;
  }

  public boolean isLeaf(Object parent) {
    return !(parent instanceof ProjectNode);
  }

  public void addTreeModelListener(TreeModelListener l) {
    listener.add(TreeModelListener.class, l);
  }

  public void removeTreeModelListener(TreeModelListener l) {
    listener.remove(TreeModelListener.class, l);
  }

  public void valueForPathChanged(TreePath path, Object newValue) {
    // only needed if you edit/change tree nodes in Jtree 
  	// itself which we don't do/support
  }
  
  private static class ProjectNode {
    private List children;
    private String title;
    public ProjectNode(List children, String title) {
      this.children = children;
      this.title = title;
    }
    public List getChildren() {
      return children;
    }
    public String getTitle() {
      return title;
    }
    @Override
    public String toString() {
      return getTitle();
    }
  }
}