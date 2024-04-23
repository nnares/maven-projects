package com.nish.api.service;

import com.nish.api.components.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService<K, V> {

  @Autowired
  CacheProvider<K, V> cacheProvider;

  public boolean addEntry(String cacheManagerName, String cacheAlias, K key, V value) {
    return cacheProvider.addEntry(cacheManagerName, cacheAlias, key, value);
  }

  public V getEntry(String cacheManagerName, String cacheAlias, K key) {
    return cacheProvider.getEntry(cacheManagerName, cacheAlias, key);
  }

  public V deleteEntry(String cacheManagerName, String cacheAlias, K key) {
    return cacheProvider.deleteEntry(cacheManagerName, cacheAlias, key);
  }

}
