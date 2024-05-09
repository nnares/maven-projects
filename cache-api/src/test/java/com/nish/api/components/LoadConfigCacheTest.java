/*
 * Copyright (c) 2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.
 */

package com.nish.api.components;

import org.ehcache.CacheManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class LoadConfigCacheTest {

  @Test
  public void testGetConfigPathForExternalPath() {
    LoadConfigCache loadConfigCache = new LoadConfigCache("externalDir", "classpathDir", "", null);
    assertEquals("file:externalDir/*.xml", loadConfigCache.getConfigPath());
  }

  @Test
  public void testGetConfigPathForClassPath() {
    LoadConfigCache loadConfigCache = new LoadConfigCache("", "classpathDir", "", null);
    assertEquals("classpath:classpathDir/*.xml", loadConfigCache.getConfigPath());
  }

  @Test
  public void testGetConfigFiles() throws IOException {
    final String path = "classpath:cache/*.xml";

    ResourcePatternResolver mockResourcePatternResolver = mock(ResourcePatternResolver.class);
    LoadConfigCache loadConfigCache = new LoadConfigCache("", "", "", mockResourcePatternResolver);

    Resource[] resourceArray = {new UrlResource("https://google.com"), new UrlResource("https://google.in")};
    doReturn(resourceArray).when(mockResourcePatternResolver).getResources(eq(path));
    List<URL> configFiles = loadConfigCache.getConfigFiles(path);

    assertEquals(2, configFiles.size());
    assertEquals("google.com", configFiles.get(0).getHost());
    assertEquals("google.in", configFiles.get(1).getHost());
  }

  @Test
  public void testPopulateCacheManagers() {
    LoadConfigCache loadConfigCache = new LoadConfigCache("", "", "cache-manager1, cache-manager2", null);
    final URL myUrl = this.getClass().getResource("/cache/multiple-managers.xml");
    Map<String, CacheManager> cacheManagerMap = loadConfigCache.populateCacheManagers(myUrl);
    assertEquals(2, cacheManagerMap.size());
  }

  @Test
  public void testPopulateCacheManagersWithNoDiscoverableCacheManagers() {
    LoadConfigCache loadConfigCache = new LoadConfigCache("", "", "", null);
    final URL myUrl = this.getClass().getResource("/cache/multiple-managers.xml");
    Map<String, CacheManager> cacheManagerMap = loadConfigCache.populateCacheManagers(myUrl);
    assertEquals(0, cacheManagerMap.size());
  }
}
