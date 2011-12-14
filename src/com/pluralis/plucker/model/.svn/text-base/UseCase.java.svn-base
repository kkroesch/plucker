package com.pluralis.plucker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.jgoodies.binding.beans.Model;

public class UseCase extends Model implements NamedEntry {

  public static final String[] complexities = new String[] 
    {"Medium", "Low", "High" };
  
  public static final String[] possibleStati = new String[] 
    { "Open", "To be clarified", "Implemented", "Tested", "Approved" };

  public static final String[] levels = new String[] 
    { "Top", "Implement" };
  
  public static class ComplexityComparator implements Comparator<String> {
    List complexities = Arrays.asList(Actor.complexities);
    public int compare(String uc1, String uc2) {
      return complexities.indexOf(uc1) - complexities.indexOf(uc2); 
    }
  }
  
  
	private String name = "";
	
  private String id = "N/A";
	
  private String description = "\n\n\n";
	
  private int priority = 2;
  
  private String status = "Open";
  
  private String complexity = "Medium";
  
  private String level = "Implement";
  
  private String preCondition = "";
  
  private String postCondition = "";
  
  private String flow = "\n\n\n";

  private Actor primaryActor = Actor.NO_ACTOR;
  
  private Actor supportingActor = Actor.NO_ACTOR;

  private ArrayList<Requirement> requirements = new ArrayList<Requirement>(3);
  
  private TestCase testCase;
  
  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);
  
  private ArrayList<Activity> activities = new ArrayList<Activity>(3);
  
  private UseCase parent;
  
  public UseCase getParent() {
    return parent;
  }
  public void setParent(UseCase parent) {
    this.parent = parent;
  }
  public String getComplexity() {
    return complexity;
  }
  public void setComplexity(String complexity) {
    this.complexity = complexity;
  }
  public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getPostCondition() {
    return postCondition;
  }
  public void setPostCondition(String postCondition) {
    this.postCondition = postCondition;
  }
  public String getPreCondition() {
    return preCondition;
  }
  public void setPreCondition(String preCondition) {
    this.preCondition = preCondition;
  }
  public String getFlow() {
    return flow;
  }
  public void setFlow(String flow) {
    this.flow = flow;
  }
  public TestCase getTestCase() {
    return testCase;
  }
  public void setTestCase(TestCase testCase) {
    this.testCase = testCase;
  }
  public ArrayList<Requirement> getRequirement() {
    return requirements;
  }
  public void setRequirement(ArrayList<Requirement> requirements) {
    this.requirements = requirements;
  }
  public Actor getPrimaryActor() {
    return primaryActor;
  }
  public void setPrimaryActor(Actor primaryActor) {
    this.primaryActor = primaryActor;
  }
  public Actor getSupportingActor() {
    return supportingActor;
  }
  public void setSupportingActor(Actor supportingActor) {
    this.supportingActor = supportingActor;
  }
  @Override
  public String toString() {
    return getId() + " " + getName();
  }
  public ArrayList<String> getRelatedDoc() {
    return relatedDocuments;
  }
  public void setRelatedDoc(ArrayList<String> docs) {
    this.relatedDocuments = docs;
  }
  public ArrayList<Activity> getActivity() {
    return activities;
  }
  public void setActivity(ArrayList<Activity> activities) {
    this.activities = activities;
  }
  public String getLevel() {
    return level;
  }
  public void setLevel(String level) {
    this.level = level;
  }
  
  
}
