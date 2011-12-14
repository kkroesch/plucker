package com.pluralis.plucker.plugin;

import junit.framework.TestCase;

public class RegistryTest extends TestCase {

  public void testDiscovery() throws Exception {
    PluginRegistry registry = new PluginRegistry(null);
    registry.discoverPlugins();
    
    assertTrue(registry.plugins.size() > 0);
  }
}
