package com.pluralis.plucker.model;

import java.util.ArrayList;
import java.util.Date;

import com.jgoodies.binding.beans.Model;

public class ChangeRequest extends Model {

  public static final String[] types = new String[] { "Feature Change", "Feature Enhancement", "Bug Report" }; 
  public static final String[] severities = new String[] { "Low", "Medium", "High", "Critical" }; 
  public static final String[] stati = new String[] { "New", "Rough Estimated", "Detailed Estimated", "Rejected", "Approved", "Pending" }; 
  
  private String id;
  
  private String summary;
  
  private String description;
  
  private String type;
  
  private String category;
  
  private String status;
  
  private String responsible;
  
  private String severity;
  
  private int priority;

  private int roughEstimation;

  private int detailedEstimation;
  
  private String reportedVersion;
  
  private String dueVersion;
  
  private Date dueDate;
  
  private ArrayList<String> affectedUseCases = new ArrayList<String>(3);
  
  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);

  public ArrayList<String> getRelatedDocuments() {
    return relatedDocuments;
  }

  public void setRelatedDocuments(ArrayList<String> relatedDocuments) {
    this.relatedDocuments = relatedDocuments;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getDueVersion() {
    return dueVersion;
  }

  public void setDueVersion(String dueVersion) {
    this.dueVersion = dueVersion;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public String getReportedVersion() {
    return reportedVersion;
  }

  public void setReportedVersion(String reportedVersion) {
    this.reportedVersion = reportedVersion;
  }

  public String getResponsible() {
    return responsible;
  }

  public void setResponsible(String responsible) {
    this.responsible = responsible;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<String> getAffectedUseCases() {
    return affectedUseCases;
  }

  public void setAffectedUseCases(ArrayList<String> affectedUseCases) {
    this.affectedUseCases = affectedUseCases;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id + " " + summary;
  }

  public int getDetailedEstimation() {
    return detailedEstimation;
  }

  public void setDetailedEstimation(int detailedEstimation) {
    this.detailedEstimation = detailedEstimation;
  }

  public int getRoughEstimation() {
    return roughEstimation;
  }

  public void setRoughEstimation(int roughEstimation) {
    this.roughEstimation = roughEstimation;
  }
  
}
