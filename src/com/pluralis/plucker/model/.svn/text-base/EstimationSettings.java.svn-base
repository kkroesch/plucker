package com.pluralis.plucker.model;

import java.text.DecimalFormat;
import java.text.Format;

public class EstimationSettings {

  private int t1 = 0;
  private int t2 = 0;
  private int t3 = 0;
  private int t4 = 0;
  private int t5 = 0;
  private int t6 = 0;
  private int t7 = 0;
  private int t8 = 0;
  private int t9 = 0;
  private int t10 = 0;
  private int t11 = 0;
  private int t12 = 0;
  private int t13 = 0;
  private int t14 = 0;
  
  private int e1 = 0;
  private int e2 = 0;
  private int e3 = 0;
  private int e4 = 0;
  private int e5 = 0;
  private int e6 = 0;
  private int e7 = 0;
  private int e8 = 0;
  
  private int avgTeamSize = 5;
  
  private double teamEfficiency = 0.35F;
  
  public int getE1() {
    return e1;
  }
  public void setE1(int e1) {
    this.e1 = e1;
  }
  public int getE2() {
    return e2;
  }
  public void setE2(int e2) {
    this.e2 = e2;
  }
  public int getE3() {
    return e3;
  }
  public void setE3(int e3) {
    this.e3 = e3;
  }
  public int getE4() {
    return e4;
  }
  public void setE4(int e4) {
    this.e4 = e4;
  }
  public int getE5() {
    return e5;
  }
  public void setE5(int e5) {
    this.e5 = e5;
  }
  public int getE6() {
    return e6;
  }
  public void setE6(int e6) {
    this.e6 = e6;
  }
  public int getE7() {
    return e7;
  }
  public void setE7(int e7) {
    this.e7 = e7;
  }
  public int getE8() {
    return e8;
  }
  public void setE8(int e8) {
    this.e8 = e8;
  }
  public int getT1() {
    return t1;
  }
  public void setT1(int t1) {
    this.t1 = t1;
  }
  public int getT10() {
    return t10;
  }
  public void setT10(int t10) {
    this.t10 = t10;
  }
  public int getT11() {
    return t11;
  }
  public void setT11(int t11) {
    this.t11 = t11;
  }
  public int getT12() {
    return t12;
  }
  public void setT12(int t12) {
    this.t12 = t12;
  }
  public int getT13() {
    return t13;
  }
  public void setT13(int t13) {
    this.t13 = t13;
  }
  public int getT14() {
    return t14;
  }
  public void setT14(int t14) {
    this.t14 = t14;
  }
  public int getT2() {
    return t2;
  }
  public void setT2(int t2) {
    this.t2 = t2;
  }
  public int getT3() {
    return t3;
  }
  public void setT3(int t3) {
    this.t3 = t3;
  }
  public int getT4() {
    return t4;
  }
  public void setT4(int t4) {
    this.t4 = t4;
  }
  public int getT5() {
    return t5;
  }
  public void setT5(int t5) {
    this.t5 = t5;
  }
  public int getT6() {
    return t6;
  }
  public void setT6(int t6) {
    this.t6 = t6;
  }
  public int getT7() {
    return t7;
  }
  public void setT7(int t7) {
    this.t7 = t7;
  }
  public int getT8() {
    return t8;
  }
  public void setT8(int t8) {
    this.t8 = t8;
  }
  public int getT9() {
    return t9;
  }
  public void setT9(int t9) {
    this.t9 = t9;
  }
  
  private final Format format = new DecimalFormat("##.##");
  
  public double calculateTeamAdjustment() {
    double sum = e1 * 1.5 + e2 * 0.5 + e3 + e4 * 0.5
               + e5 + e6 * 2 + e7 * -1 + e8 * -1;
    
    return 1.4 + (-0.03 * sum);
  }
  
  public String formatTeamAdjustment() {
    return format.format(calculateTeamAdjustment());
  }
  
  public double calculateTechnicalAdjustment() {
    double sum = t1 * 2 + t2 * 2 + t3 + t4 + t5 + t6 * 0.5 + t7 * 0.5 
    + t8 * 2 + t9 + t10 + t11 + t12 + t13;
    
    return 0.6 + (0.01 * sum);
  }

  public String formatTechnicalAdjustment() {
    return format.format(calculateTechnicalAdjustment());
  }

  public int getAvgTeamSize() {
    return avgTeamSize;
  }
  
  public void setAvgTeamSize(int avgTeamSize) {
    this.avgTeamSize = avgTeamSize;
  }
  
  public double getTeamEfficiency() {
    return teamEfficiency;
  }
  
  public void setTeamEfficiency(double teamEfficiency) {
    this.teamEfficiency = teamEfficiency;
  }
  
  public int hoursPerDay() {
    return 8;
  }
  
  public int daysPerWeek() {
    return 5;
  }
}
