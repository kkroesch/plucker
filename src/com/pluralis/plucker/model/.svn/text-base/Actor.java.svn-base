package com.pluralis.plucker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.jgoodies.binding.beans.Model;

public class Actor extends Model implements NamedEntry {

  public static final Actor NO_ACTOR = new Actor();
  
  public static final String[] complexities = new String[] { "Complex", "Medium", "Low" };

  private String id = "";

  private String name = "NO ACTOR";

  private String description = "";

  private String complexity = "";
  
  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);
  
  public static class ComplexityComparator implements Comparator<String> {
    List complexities = Arrays.asList(Actor.complexities);
    public int compare(String actor1, String actor2) {
      return complexities.indexOf(actor1) - complexities.indexOf(actor2); 
    }
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
  
  public ArrayList<String> getRelatedDoc() {
    return relatedDocuments;
  }
  public void setRelatedDoc(ArrayList<String> docs) {
    this.relatedDocuments = docs;
  }

  @Override
  public String toString() {
    return getId() + " " + getName();
  }

}
