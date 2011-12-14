package com.pluralis.plucker.model;

import java.util.ArrayList;

import com.jgoodies.binding.beans.Model;

public class TestCase extends Model implements NamedEntry {

  public enum Result { FAILED, SUCCESS, NOT_RUN };
  
  public static final TestCase NO_TEST = new TestCase() {
    @Override
    public String getId() {
      return "0";
    }
  };
  
  private String id = "T-1";
  
  private String name = "Testcase not defined";
  
  private String testClass = "org.junit.Test";
  
  private Result result = Result.NOT_RUN;
  
  private ArrayList<String> relatedDocuments = new ArrayList<String>(3);
  
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

  public String getResult() {
    return result.toString();
  }

  public void setResult(String result) {
    this.result = Result.valueOf(result);
  }

  public String getDescription() {
    return "";
  }

  public String getTestClass() {
    return testClass;
  }

  public void setTestClass(String testClass) {
    this.testClass = testClass;
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
