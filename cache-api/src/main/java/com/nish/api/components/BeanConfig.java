package com.nish.api.components;


import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourcePatternResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class BeanConfig {

  @Bean(name = "cacheManagerMap")
  public Map<String, CacheManager> getCacheManagerMap(@Value("${cache.config.external}") String externalDir,
                                                      @Value("${cache.config.classpath}") String classpathDir,
                                                      @Value("${cache.discoverable.cacheManagers}") String discoverableCacheManagers,
                                                      ResourcePatternResolver resourceResolver) {
    return new LoadConfigCache(externalDir, classpathDir, discoverableCacheManagers, resourceResolver).getCacheManagerMap();
  }

}
