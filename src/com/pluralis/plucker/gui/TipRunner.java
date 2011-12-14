package com.pluralis.plucker.gui;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.jdesktop.swingx.JXTipOfTheDay;
import org.jdesktop.swingx.tips.TipLoader;
import org.jdesktop.swingx.tips.TipOfTheDayModel;

public class TipRunner implements Runnable {

  private Application app;
  
  public TipRunner(Application app) {
    this.app = app;
  }

  public void run() {
    Properties tips = new Properties();
    try {
      tips.load(new FileInputStream("tipoftheday"));
      
      TipOfTheDayModel model = TipLoader.load(tips);
      JXTipOfTheDay totd = new JXTipOfTheDay(model);
      
      Preferences prefs = Preferences.userNodeForPackage(Main.class);
      totd.showDialog(app.rootWindow(), prefs);
    } catch (HeadlessException e) {
      app.handle(e);
    } catch (FileNotFoundException e) {
      app.handle(e);
    } catch (IOException e) {
      app.handle(e);
    }
  }
}
