package com.nish.api.components;

import com.nish.api.exception.CacheApiException;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.Configuration;
import org.ehcache.xml.multi.XmlMultiConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadConfigCache {
  private final ResourcePatternResolver resourceResolver;
  private final String externalDir;
  private final String classpathDir;
  private final String discoverableCacheManagers;

  public LoadConfigCache(String externalDir, String classpathDir, String discoverableCacheManagers, ResourcePatternResolver resourceResolver) {
    this.resourceResolver = resourceResolver;
    this.externalDir = externalDir;
    this.classpathDir = classpathDir;
    this.discoverableCacheManagers = discoverableCacheManagers;
  }

  public String getConfigPath() {
    final String EXTERNAL_CONFIG_DIR = "file:%s/*.xml";
    final String CLASSPATH_CONFIG_DIR = "classpath:%s/*.xml";

    return externalDir.isEmpty() ? String.format(CLASSPATH_CONFIG_DIR, classpathDir) : String.format(EXTERNAL_CONFIG_DIR, externalDir);
  }

  public Map<String, CacheManager> getCacheManagerMap() {
    return getConfigFiles(getConfigPath())
        .stream()
        .collect(HashMap::new, (m, e) -> m.putAll(populateCacheManagers(e)), HashMap::putAll);
  }

  List<URL> getConfigFiles(String path) {
    List<URL> urls = new ArrayList<>();
    try {
      for (Resource resource : resourceResolver.getResources(path)) {
        urls.add(resource.getURL());
      }
    } catch (IOException e) {
      throw new CacheApiException(e + "Unable to get cache configuration file");
    }
    return urls;
  }

  Map<String, CacheManager> populateCacheManagers(URL url) {
    Map<String, CacheManager> map = new HashMap<>();
    XmlMultiConfiguration multipleConfiguration = XmlMultiConfiguration
        .from(url)
        .build();

    for (String cacheManagerName : multipleConfiguration.identities()) {
      if (isDiscoverable(cacheManagerName)) {
        Configuration configuration = multipleConfiguration.configuration(cacheManagerName);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(configuration);
        cacheManager.init();
        map.put(cacheManagerName, cacheManager);
      }
    }
    return map;
  }

  private boolean isDiscoverable(String cacheManagerName) {
    return discoverableCacheManagers.contains(cacheManagerName);
  }
}
