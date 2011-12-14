/*
 * Created on 22.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.apache.log4j.Logger;

import com.pluralis.plucker.gui.action.ExportProject;
import com.pluralis.plucker.gui.action.FileExit;
import com.pluralis.plucker.gui.action.FileImport;
import com.pluralis.plucker.gui.action.FileOpen;
import com.pluralis.plucker.gui.action.FileSave;
import com.pluralis.plucker.gui.action.HelpAbout;
import com.pluralis.plucker.gui.action.HelpContent;
import com.pluralis.plucker.gui.action.ReportBug;
import com.pluralis.plucker.gui.action.Search;
import com.pluralis.plucker.plugin.AboutPluginAction;
import com.pluralis.plucker.plugin.Plugin;
import com.pluralis.plucker.plugin.PluginAction;
import com.pluralis.plucker.plugin.PluginRegistry;


public class MenuBar {

  private JMenu pluginMenu;

  private JMenu helpMenu;

  private JMenu aboutPlugins;

  public Action addUseCase;

  public Action addTestCase;

  public Action addActor;

  public Action addRequirement;

  public Action addChangeRequest;
  
  public Action addActivity;

  public Action helpAboutAction;

  public Action fileOpenAction;

  public Action fileSaveAction;

  public Action fileImportAction;

  public Action fileExportAction;

  public Action fileExitAction;

  public Action showSearchDialog;

  public Action reportSrs;

  public Action reportEstimation;

  public Action reportStatus;
  
  public Action checkConsistency;

  public Action viewComponents;

  public Action viewDesktop;

  public Action viewWelcome;

  public Action viewEstimation;

  public Action viewSvnStatistics;

  public Action viewScriptConsole;

  public Action viewLogViewer;
  
  public Action viewGlossary;

  public Action viewTestRunner;

  public Action setEstimationParam;

  public Action showOptionDialog;

  public Action setProjectOptions;
  
  public Action helpContent;

  public Action reportBugAction;

  private Application application;

  private Logger log = Logger.getLogger(MenuBar.class);

  public MenuBar(Application application) {
    this.application = application;
  }

  public JMenuBar create() {
    JMenuBar comp = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    fileOpenAction = new FileOpen(application);
    fileMenu.add(fileOpenAction);
    fileSaveAction = new FileSave(application);
    fileMenu.add(fileSaveAction);
    fileMenu.addSeparator();
    fileImportAction = new FileImport(application);
    fileMenu.add(fileImportAction);
    fileExportAction = new ExportProject();
    fileMenu.add(fileExportAction);
    fileMenu.addSeparator();
    fileExitAction = new FileExit(application);
    fileMenu.add(fileExitAction);
    comp.add(fileMenu);

    showSearchDialog = new Search(application);
    
    JMenu helpMenu = new JMenu("Help");
    helpContent = new HelpContent(application);
    helpAboutAction = new HelpAbout(application);
    helpMenu.add(helpAboutAction);
    reportBugAction = new ReportBug(application);
    comp.add(helpMenu);
    
    PluginRegistry registry = new PluginRegistry(application);
    registry.discoverPlugins();
    for (Plugin plugin : registry.plugins) {
      pluginMenu.add(new PluginAction(plugin));
      aboutPlugins.add(new AboutPluginAction(plugin, application));
    }
    return comp;
  }
}