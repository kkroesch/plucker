package com.pluralis.plucker.model;

import java.util.ArrayList;

import com.jgoodies.binding.beans.Model;

public class Requirement extends Model implements NamedEntry {

  private String id;
  
  private String name = "";
  
  private String description = "";

  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);
  
  public ArrayList<String> getRelatedDocuments() {
    return relatedDocuments;
  }

  public void setRelatedDocuments(ArrayList<String> relatedDocuments) {
    this.relatedDocuments = relatedDocuments;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return getId() + " " + getName();
  }
  
  
}
