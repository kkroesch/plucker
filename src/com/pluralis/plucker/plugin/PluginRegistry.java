package com.pluralis.plucker.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.spi.ServiceRegistry;

import com.pluralis.plucker.gui.Application;

public class PluginRegistry {

  private Logger log = Logger.getLogger("PluginRegistry");
  
  private Application application;
  
  public final List<Plugin> plugins = new ArrayList<Plugin>();
  
  public PluginRegistry(Application application) {
    this.application = application;
  }

  public void discoverPlugins() {
    plugins.clear();
    
    File pluginDir = new File("plugins");
    File[] jarFiles = pluginDir.listFiles(new JarFileFilter());
    
    // Handle malformed or missing plugin directory
    if (jarFiles == null) {
      log.severe("Plugin directory not found.");
      return;
    }

    for (File jarFile : jarFiles) {
      log.info("Searching for plugin in " + jarFile);

      // Try to load plugin classes
      ClassLoader classLoader;
      try {
        classLoader = new URLClassLoader(new URL[]{jarFile.toURL()}, PluginRegistry.class.getClassLoader());
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }

      for (Iterator iter = ServiceRegistry.lookupProviders(Plugin.class, classLoader); iter.hasNext();) {
        Plugin plugin = (Plugin) iter.next();
        plugin.setApplication(application);
        log.info("Found plugin information for " + plugin.getName() + " Version " + plugin.getVersion());
        plugins.add(plugin);
      }
    }
  }
  
  public static class JarFileFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      return name.endsWith(".jar");
    }
  }
}
