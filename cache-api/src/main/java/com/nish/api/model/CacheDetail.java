package com.nish.api.model;

import org.ehcache.Cache;

import java.util.Objects;

public class CacheDetail<K, V> {
  private final Cache<K, V> cache;
  private final Class<K> keyType;
  private final Class<V> valueType;

  public CacheDetail(Cache<K, V> cache, Class<K> keyType, Class<V> valueType) {
    this.cache = cache;
    this.keyType = keyType;
    this.valueType = valueType;
  }

  public Cache<K, V> getCache() {
    return cache;
  }

  public Class<K> getKeyType() {
    return keyType;
  }

  public Class<V> getValueType() {
    return valueType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CacheDetail)) return false;
    CacheDetail<?, ?> that = (CacheDetail<?, ?>) o;
    return Objects.equals(cache, that.cache) && Objects.equals(keyType, that.keyType) && Objects.equals(valueType, that.valueType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cache, keyType, valueType);
  }
}
