package com.pluralis.plucker.model;

import java.util.Date;

import com.jgoodies.binding.beans.Model;

public class Activity extends Model implements NamedEntry {

  public enum Status { Open, Pending, Closed, Dismissed }
  
  public static final String[] STATUS_VALUES = new String[] {
      Status.Open.toString(), Status.Pending.toString(),
      Status.Closed.toString(), Status.Dismissed.toString()
  };
  
  private String id = "ACT-000";
  
  private String name = "NO ACTIVITY";
  
  private String description = "Default activity";
  
  private Date started = new Date();
  
  private Date dueDate = new Date();
  
  private int estimatedHours = 0;
  
  private int realHours = 0;
  
  private int remainingHours = 0;
  
  private Status status = Status.Open;
  
  private String author = "NO AUTHOR";
  

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
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

  public Date getStarted() {
    return started;
  }

  public void setStarted(Date started) {
    this.started = started;
  }

  public String getStatus() {
    return status.toString();
  }

  public void setStatus(String status) {
    this.status = Status.valueOf(status);
  }

  public int getEstimatedHours() {
    return estimatedHours;
  }

  public void setEstimatedHours(int estimatedHours) {
    this.estimatedHours = estimatedHours;
  }

  public int getRealHours() {
    return realHours;
  }

  public void setRealHours(int realHours) {
    this.realHours = realHours;
  }

  public int getRemainingHours() {
    return remainingHours;
  }

  public void setRemainingHours(int remainingHours) {
    this.remainingHours = remainingHours;
  }
  
  public int getAvailableHours() {
    return estimatedHours - (realHours + remainingHours); 
  }
  /**
   * Dummy mutator implementation for JavaBean compatibility.
   * @param hours Will be ignored.
   */
  public void setAvailableHours(int hours) {
    // Immutable
  }

  @Override
  public String toString() {
    return getId() + " " + getName();
  }
  
}