package com.pluralis.plucker.model;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;

import com.jgoodies.binding.beans.Model;
import com.pluralis.plucker.gui.action.AddChangeRequest;
import com.pluralis.plucker.util.BeanUtil;

public class Project extends Model {

  private ArrayList<UseCase> usecases = new ArrayList<UseCase>();
  private ArrayList<Actor> actors = new ArrayList<Actor>();
  private ArrayList<Requirement> requirements = new ArrayList<Requirement>();
  private ArrayList<Activity> activities = new ArrayList<Activity>();
  
  private ArrayList<TestCase> testCases = new ArrayList<TestCase>();
  private ArrayList<ChangeRequest> changeRequests = new ArrayList<ChangeRequest>();
  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);
  
  private BeanTableModel usecaseTableModel;
  private BeanTableModel actorTableModel;
  private BeanTableModel requirementTableModel;
  private BeanTableModel testCaseTableModel;
  private BeanTableModel changeRequestTableModel;
  
  private BeanTableModel activityTableModel;
  
  private ProjectTreeModel projectTreeModel;
  
  private String name = "project";
  
  private String description = "[Description goes here]";
  
  private EstimationSettings estimationSettings = new EstimationSettings();
  
  private Map<String, GlossaryEntry> glossary = new HashMap<String, GlossaryEntry>();
  
  private boolean dirty = true;
  
  public Project() {
    addPropertyChangeListener(new ProjectChangeListener(this));
  }

  public boolean isDirty() {
    return dirty;
  }
  
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }
  
  public ArrayList<ChangeRequest> getChangeRequest() {
    return changeRequests;
  }

  public void setChangeRequest(ArrayList<ChangeRequest> changeRequests) {
    this.changeRequests = changeRequests;
  }

  public ArrayList<UseCase> getUsecase() {
    return usecases;
  }
  public void setUsecase(ArrayList<UseCase> usecases) {
    this.usecases = usecases;
  }
    
  public ArrayList<Actor> getActor() {
    return actors;
  }

  public void setActor(ArrayList<Actor> actors) {
    this.actors = actors;
  }
  
  public ArrayList<TestCase> getTestCase() {
    return testCases;
  }

  public void setTestCase(ArrayList<TestCase> testCases) {
    this.testCases = testCases;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BeanTableModel getUsecaseModel() {
    if (usecaseTableModel != null) return usecaseTableModel;
    
    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", UseCase.class, "id"),
        BeanUtil.createDescriptor("Name", UseCase.class, "name"),
        BeanUtil.createDescriptor("Description", UseCase.class, "description"),
        BeanUtil.createDescriptor("Complexity", UseCase.class, "complexity"),
        BeanUtil.createDescriptor("Prio.", UseCase.class, "priority")
    };
    
    usecaseTableModel = new BeanTableModel(descriptors, getUsecase());
    return usecaseTableModel;
  }

  public BeanTableModel getActorTableModel() {
    if (actorTableModel != null) return actorTableModel;
    
    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", Actor.class, "id"),
        BeanUtil.createDescriptor("Name", Actor.class, "name"),
        BeanUtil.createDescriptor("Description", Actor.class, "description"),
        BeanUtil.createDescriptor("complexity", Actor.class, "complexity")
    };
    
    actorTableModel = new BeanTableModel(descriptors, getActor());
    return actorTableModel;
  }
  
  public BeanTableModel getRequirementsTableModel() {
    if (requirementTableModel != null) return requirementTableModel;

    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", Requirement.class, "id"),
        BeanUtil.createDescriptor("Name", Requirement.class, "name"),
        BeanUtil.createDescriptor("Description", Requirement.class, "description")
    };
    
    requirementTableModel = new BeanTableModel(descriptors, getRequirement());
    return requirementTableModel;
  }
  
  public BeanTableModel getChangeRequestTableModel() {
    if (changeRequestTableModel != null) return changeRequestTableModel;

    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", ChangeRequest.class, "id"),
        BeanUtil.createDescriptor("Summary", ChangeRequest.class, "summary")
    };
    
    changeRequestTableModel = new BeanTableModel(descriptors, getChangeRequest());
    return changeRequestTableModel;
  }

  public BeanTableModel getTestCaseTableModel() {
    if (testCaseTableModel != null) return testCaseTableModel;

    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", TestCase.class, "id"),
        BeanUtil.createDescriptor("Summary", TestCase.class, "name"),
        BeanUtil.createDescriptor("Class/Script", TestCase.class, "testClass"),
        BeanUtil.createDescriptor("Result", TestCase.class, "result")
    };
    
    testCaseTableModel = new BeanTableModel(descriptors, getTestCase());
    return testCaseTableModel;
  }

  public BeanTableModel getActivityTableModel() {
    if (activityTableModel != null) return activityTableModel;
    
    PropertyDescriptor[] descriptors = new PropertyDescriptor[] {
        BeanUtil.createDescriptor("ID", Activity.class, "id"),
        BeanUtil.createDescriptor("Name", Activity.class, "name"),
        BeanUtil.createDescriptor("Description", Activity.class, "description"),
        BeanUtil.createDescriptor("Due Date", Activity.class, "dueDate"),
        BeanUtil.createDescriptor("Status", Activity.class, "status"),
        BeanUtil.createDescriptor("Estimated", Activity.class, "estimatedHours"),
        BeanUtil.createDescriptor("Real", Activity.class, "realHours"),
        BeanUtil.createDescriptor("Remaining", Activity.class, "remainingHours"),
        BeanUtil.createDescriptor("Available", Activity.class, "availableHours")
    };
    
    activityTableModel = new BeanTableModel(descriptors, getActivity());
    return activityTableModel;
  }
  
  public ProjectTreeModel getTreeModel() {
    if (projectTreeModel != null) return projectTreeModel;
    
    projectTreeModel = new ProjectTreeModel(this);
    return projectTreeModel;
  }
  
  public ListModel getActorListModel() {
    return new AbstractListModel() {
      public Object getElementAt(int index) {
        return getActor().get(index);
      }

      public int getSize() {
        return getActor().size();
      }};
  }

  public ListModel getUseCaseListModel() {
    return new AbstractListModel() {
      public Object getElementAt(int index) {
        return getUsecase().get(index);
      }
      public int getSize() {
        return getUsecase().size();
      }};
  }

  public ArrayList<Requirement> getRequirement() {
    return requirements;
  }

  public void setRequirement(ArrayList<Requirement> requirements) {
    this.requirements = requirements;
  }

  public EstimationSettings getEstimationSettings() {
    return estimationSettings;
  }

  public void setEstimationSettings(EstimationSettings estimationSettings) {
    this.estimationSettings = estimationSettings;
  }

  public void generateId(Actor actor) {
    actor.setId("A-" + actors.size());
  }
  
  public void generateId(UseCase usecase) {
    usecase.setId("UC-" + usecases.size());
  }

  public void generateId(Requirement requirement) {
    requirement.setId("R-" + requirements.size());
  }

  public void generateId(Activity activity) {
    activity.setId("ACT-" + activities.size());
  }
  
  public void generateId(ChangeRequest request) {
    request.setId("CR-" + changeRequests.size());
  }
  
  public void generateId(TestCase test) {
    test.setId("T-" + testCases.size());
  }
  
  public ArrayList<Activity> getActivity() {
    return activities;
  }
  
  public void setActivity(ArrayList<Activity> activities) {
    this.activities = activities;
  }
  
  public Map<String, GlossaryEntry> getGlossary() {
    return glossary;
  }
  
  public void setGlossary(Map<String, GlossaryEntry> glossary) {
    this.glossary = glossary;
  }
  
  public ArrayList<String> getRelatedDocuments() {
    return relatedDocuments;
  }
  
  public void setRelatedDocuments(ArrayList<String> relatedDocuments) {
    this.relatedDocuments = relatedDocuments;
  }
  
  public AbstractListModel assignmentModel() {
    final List<UseCase> assignment = new ArrayList<UseCase>(getUsecase().size() + 2);
    assignment.addAll(getUsecase());
    
    return new AbstractListModel() {
      public Object getElementAt(int index) {
        return assignment.get(index).toString();
      }

      public int getSize() {
        return assignment.size();
      }
    };
  }
  
  @Override
  public String toString() {
    return "Project " + getName();
  }
  
}