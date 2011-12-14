/*
 * Created on 26.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.model;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.pluralis.plucker.gui.Main;

public class ClientOptions {

  private Preferences properties;
  
  public ClientOptions() {
    properties = Preferences.userNodeForPackage(Main.class);
  }
  
  public void store() {
    try {
      properties.flush();
    } catch (BackingStoreException e) {
      throw new RuntimeException(e);
    }
  }
  
  public void setProperty(String key, String value) {
    properties.put(key, value);
  }
  
  public String getProperty(String key) {
    Object value = properties.get(key, "");
    return value == null ? "N/A" : value.toString();
  }
  
  public void setServerUrl(String url) {
    properties.put("server.url", url);
  }
  public String getServerUrl() {
    String url = properties.get("server.url", "http://localhost/");
    return url;
  }
  
  public boolean isConfirm(String option) {
    Object confirm = properties.get("confirm." + option, "no");
    return "yes".equals(confirm);
  }
  
  public void neverConfirm(String option) {
    properties.put("confirm." + option, "yes");
  }
  
  public int defaultAnswer(String option) {
    return properties.getInt("confirm." + option, 1);
  }
  
  public void setLastUser(String user) {
    properties.put("last.user", user);
  }
  
  public String getLastUser() {
    return properties.get("last.user", "prepare");
  }
  
  public File getLastProjectLocation() {
    String path = properties.get("last.project.location", System.getProperty("user.home"));
    return new File(path);
  }
  
  public void setLastProjectLocation(File file) {
    properties.put("last.project.location", file.getAbsolutePath());
  }
}
