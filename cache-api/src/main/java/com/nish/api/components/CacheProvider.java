package com.nish.api.components;

import com.nish.api.exception.CacheApiException;
import com.nish.api.model.CacheDetail;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Component
public class CacheProvider<K, V> {
  private final Map<String, CacheManager> cacheManagerMap;

  @Autowired
  public CacheProvider(@Qualifier("cacheManagerMap") Map<String, CacheManager> cacheManagerMap) {
    this.cacheManagerMap = cacheManagerMap;
  }

  public boolean addEntry(String cacheManagerName, String cacheAlias, K key, V value) {
    CacheDetail<K, V> cacheDetail = getCacheDetails(cacheManagerName, cacheAlias);
    key = parseKey(cacheDetail, key);
    value = parseValue(cacheDetail, value);
    cacheDetail.getCache().put(key, value);
    return true;
  }

  public V getEntry(String cacheManagerName, String cacheAlias, K key) {
    CacheDetail<K, V> cacheDetail = getCacheDetails(cacheManagerName, cacheAlias);
    key = parseKey(cacheDetail, key);
    return cacheDetail.getCache().get(key);
  }

  public V deleteEntry(String cacheManagerName, String cacheAlias, K key) {
    CacheDetail<K, V> cacheDetail = getCacheDetails(cacheManagerName, cacheAlias);
    key = parseKey(cacheDetail, key);
    V value = cacheDetail.getCache().get(key);
    cacheDetail.getCache().remove(key);
    return value;
  }

  @SuppressWarnings("unchecked")
  private CacheDetail<K, V> getCacheDetails(String cacheManagerName, String cacheAlias) {
    CacheManager cacheManager = cacheManagerMap.get(cacheManagerName);
    if (cacheManager == null) {
      throw new CacheApiException("CacheManager with name " + cacheManagerName + " is not found.");
    }
    if (cacheManager.getRuntimeConfiguration().getCacheConfigurations().get(cacheAlias) == null) {
      throw new CacheApiException("Cache with cache-alias " + cacheAlias + " is not found.");
    }
    Class<K> keyType = (Class<K>) cacheManager.getRuntimeConfiguration().getCacheConfigurations().get(cacheAlias).getKeyType();
    Class<V> valueType = (Class<V>) cacheManager.getRuntimeConfiguration().getCacheConfigurations().get(cacheAlias).getValueType();
    Cache<K, V> cache = cacheManager.getCache(cacheAlias, keyType, valueType);
    return new CacheDetail<>(cache, keyType, valueType);
  }

  private K parseKey(CacheDetail<K, V> cacheDetail, K key) {
    String strKey = String.valueOf(key);
    K parsedKey;
    try {
      parsedKey = cacheDetail.getKeyType().getConstructor(String.class).newInstance(strKey);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new CacheApiException("Invalid key type " + strKey);
    }
    return parsedKey;
  }

  private V parseValue(CacheDetail<K, V> cacheDetail, V value) {
    String strValue = String.valueOf(value);
    V parsedValue;
    try {
      parsedValue = cacheDetail.getValueType().getConstructor(String.class).newInstance(strValue);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new CacheApiException("Invalid value type " + strValue);
    }
    return parsedValue;
  }
}
