/*
 * Copyright (c) 2024 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.
 */
package com.nish.api.components;

import com.nish.api.exception.CacheApiException;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class CacheProviderTest<K, V> {

  final K key = (K) new Integer(1);
  final V value = (V) new Long(100);
  final Class<Integer> keyType = Integer.class;
  final Class<Long> valueType = Long.class;
  final String cacheManagerName = "cacheManager";
  final String cacheAlias = "cache";

  private CacheProvider<K, V> cacheProvider;
  private Map<String, CacheManager> cacheManagerMap;

  @BeforeEach
  void setup() {
    cacheManagerMap = mock(HashMap.class);
    cacheProvider = new CacheProvider<>(cacheManagerMap);
  }

  @Test
  public void testAddEntry() {
    Cache<K, V> mockCache = mock(Cache.class);
    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);
    org.ehcache.config.CacheConfiguration<?, ?> mockCacheConfiguration = mock(org.ehcache.config.CacheConfiguration.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(mockCacheConfiguration).when(mockConfigMap).get(anyString());
    doReturn(keyType).when(mockCacheConfiguration).getKeyType();
    doReturn(valueType).when(mockCacheConfiguration).getValueType();
    doReturn(mockCache).when(mockCacheManager).getCache(eq(cacheAlias), eq(keyType), eq(valueType));
    doNothing().when(mockCache).put(any(), any());

    assertTrue(cacheProvider.addEntry(cacheManagerName, cacheAlias, key, value));

    verify(cacheManagerMap, times(1)).get(cacheManagerName);
    verify(mockCacheManager, times(3)).getRuntimeConfiguration();
    verify(mockConfig, times(3)).getCacheConfigurations();
    verify(mockConfigMap, times(3)).get(cacheAlias);
    verify(mockCacheConfiguration, times(1)).getKeyType();
    verify(mockCacheConfiguration, times(1)).getValueType();
    verify(mockCacheManager, times(1)).getCache(cacheAlias, keyType, valueType);
    verify(mockCache).put(key, value);
    verify(mockCache, times(1)).put(key, value);
  }

  @Test
  public void testGetEntry() {
    Cache<K, V> mockCache = mock(Cache.class);
    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);
    org.ehcache.config.CacheConfiguration<?, ?> mockCacheConfiguration = mock(org.ehcache.config.CacheConfiguration.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(mockCacheConfiguration).when(mockConfigMap).get(eq(cacheAlias));
    doReturn(keyType).when(mockCacheConfiguration).getKeyType();
    doReturn(valueType).when(mockCacheConfiguration).getValueType();
    doReturn(mockCache).when(mockCacheManager).getCache(eq(cacheAlias), eq(keyType), eq(valueType));
    doReturn(value).when(mockCache).get(eq(key));

    assertEquals(value, cacheProvider.getEntry(cacheManagerName, cacheAlias, key));

    verify(cacheManagerMap, times(1)).get(cacheManagerName);
    verify(mockCacheManager, times(3)).getRuntimeConfiguration();
    verify(mockConfig, times(3)).getCacheConfigurations();
    verify(mockConfigMap, times(3)).get(cacheAlias);
    verify(mockCacheConfiguration, times(1)).getKeyType();
    verify(mockCacheConfiguration, times(1)).getValueType();
    verify(mockCacheManager, times(1)).getCache(cacheAlias, keyType, valueType);
    verify(mockCache).get(key);
    verify(mockCache, times(1)).get(key);
  }

  @Test
  public void testDeleteEntry() {
    Cache<K, V> mockCache = mock(Cache.class);
    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);
    org.ehcache.config.CacheConfiguration<?, ?> mockCacheConfiguration = mock(org.ehcache.config.CacheConfiguration.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(mockCacheConfiguration).when(mockConfigMap).get(anyString());
    doReturn(keyType).when(mockCacheConfiguration).getKeyType();
    doReturn(valueType).when(mockCacheConfiguration).getValueType();
    doReturn(mockCache).when(mockCacheManager).getCache(eq(cacheAlias), eq(keyType), eq(valueType));
    doReturn(value).when(mockCache).get(eq(key));
    doNothing().when(mockCache).remove(eq(key));

    assertEquals(value, cacheProvider.deleteEntry(cacheManagerName, cacheAlias, key));

    verify(cacheManagerMap, times(1)).get(cacheManagerName);
    verify(mockCacheManager, times(3)).getRuntimeConfiguration();
    verify(mockConfig, times(3)).getCacheConfigurations();
    verify(mockConfigMap, times(3)).get(cacheAlias);
    verify(mockCacheConfiguration, times(1)).getKeyType();
    verify(mockCacheConfiguration, times(1)).getValueType();
    verify(mockCacheManager, times(1)).getCache(cacheAlias, keyType, valueType);
    verify(mockCache).get(key);
    verify(mockCache, times(1)).get(key);
    verify(mockCache).remove(key);
    verify(mockCache, times(1)).remove(key);
  }

  @Test
  public void testGetEntryForMissingCacheManager() {
    String wrongCacheManagerName = "cacheManagerNotExist";
    doReturn(null).when(cacheManagerMap).get(eq(wrongCacheManagerName));

    Throwable exception = assertThrows(CacheApiException.class, () -> cacheProvider.getEntry(wrongCacheManagerName, "cache", (K) "1"));
    assertEquals("CacheManager with name " + wrongCacheManagerName + " is not found.", exception.getMessage());
  }

  @Test
  public void testGetEntryForMissingCacheAlias() {
    String wrongCacheAlias = "cacheNotExist";

    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(null).when(mockConfigMap).get(eq(wrongCacheAlias));

    Throwable exception = assertThrows(CacheApiException.class, () -> cacheProvider.getEntry(cacheManagerName, wrongCacheAlias, (K) "1"));
    assertEquals("Cache with cache-alias " + wrongCacheAlias + " is not found.", exception.getMessage());
    verify(mockCacheManager, times(1)).getRuntimeConfiguration();
    verify(mockConfig, times(1)).getCacheConfigurations();
  }

  @Test
  public void testParseKeyForRuntimeException() {
    K wrongKey = (K) "one";

    Cache<K, V> mockCache = mock(Cache.class);
    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);
    org.ehcache.config.CacheConfiguration<?, ?> mockCacheConfiguration = mock(org.ehcache.config.CacheConfiguration.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(mockCacheConfiguration).when(mockConfigMap).get(anyString());
    doReturn(keyType).when(mockCacheConfiguration).getKeyType();
    doReturn(valueType).when(mockCacheConfiguration).getValueType();
    doReturn(mockCache).when(mockCacheManager).getCache(eq(cacheAlias), eq(keyType), eq(valueType));

    Throwable exception = assertThrows(CacheApiException.class, () -> cacheProvider.getEntry(cacheManagerName, cacheAlias, wrongKey));
    assertEquals("Invalid key type " + wrongKey, exception.getMessage());
    verify(mockCacheConfiguration, times(1)).getKeyType();

    verify(cacheManagerMap, times(1)).get(cacheManagerName);
    verify(mockCacheManager, times(3)).getRuntimeConfiguration();
    verify(mockConfig, times(3)).getCacheConfigurations();
    verify(mockConfigMap, times(3)).get(cacheAlias);
    verify(mockCacheConfiguration, times(1)).getKeyType();
    verify(mockCacheManager, times(1)).getCache(cacheAlias, keyType, valueType);
    verify(mockCache, never()).get(wrongKey);
  }

  @Test
  public void testParseValueForRuntimeException() {
    V wrongValue = (V) "hundred";

    Cache<K, V> mockCache = mock(Cache.class);
    CacheManager mockCacheManager = mock(CacheManager.class);
    org.ehcache.config.Configuration mockConfig = mock(org.ehcache.config.Configuration.class);
    Map<String, org.ehcache.config.CacheConfiguration<?, ?>> mockConfigMap = mock(HashMap.class);
    org.ehcache.config.CacheConfiguration<?, ?> mockCacheConfiguration = mock(org.ehcache.config.CacheConfiguration.class);

    doReturn(mockCacheManager).when(cacheManagerMap).get(eq(cacheManagerName));
    doReturn(mockConfig).when(mockCacheManager).getRuntimeConfiguration();
    doReturn(mockConfigMap).when(mockConfig).getCacheConfigurations();
    doReturn(mockCacheConfiguration).when(mockConfigMap).get(anyString());
    doReturn(keyType).when(mockCacheConfiguration).getKeyType();
    doReturn(valueType).when(mockCacheConfiguration).getValueType();
    doReturn(mockCache).when(mockCacheManager).getCache(eq(cacheAlias), eq(keyType), eq(valueType));

    Throwable exception = assertThrows(CacheApiException.class, () -> cacheProvider.addEntry(cacheManagerName, cacheAlias, key, wrongValue));
    assertEquals("Invalid value type " + wrongValue, exception.getMessage());
    verify(cacheManagerMap, times(1)).get(cacheManagerName);
    verify(mockCacheManager, times(3)).getRuntimeConfiguration();
    verify(mockConfig, times(3)).getCacheConfigurations();
    verify(mockConfigMap, times(3)).get(cacheAlias);
    verify(mockCacheConfiguration, times(1)).getKeyType();
    verify(mockCacheConfiguration, times(1)).getValueType();
    verify(mockCacheManager, times(1)).getCache(cacheAlias, keyType, valueType);
    verify(mockCache, never()).put(key, wrongValue);
  }
}
