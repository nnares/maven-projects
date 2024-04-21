package com.nish.api.controller;

import com.nish.api.exception.CacheApiException;
import com.nish.api.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController<K, V> {
  @Autowired
  CacheService<K, V> cacheService;

  @GetMapping(value = "/{cacheManager}/{cacheAlias}/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
  public V getEntry(@PathVariable("cacheManager") String cacheManagerName,
                    @PathVariable("cacheAlias") String cacheAlias,
                    @PathVariable("key") K key) {
    return cacheService.getEntry(cacheManagerName, cacheAlias, key);
  }

  @PostMapping(value = "/{cacheManager}/{cacheAlias}/{key}/{value}")
  public ResponseEntity<Object> addEntry(@PathVariable("cacheManager") String cacheManagerName,
                                         @PathVariable("cacheAlias") String cacheAlias,
                                         @PathVariable("key") K key,
                                         @PathVariable("value") V value) {

    try {
      boolean isSuccess = cacheService.addEntry(cacheManagerName, cacheAlias, key, value);
      return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    } catch (CacheApiException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/{cacheManager}/{cacheAlias}/{key}")
  public V deleteEntry(@PathVariable("cacheManager") String cacheManagerName,
                       @PathVariable("cacheAlias") String cacheAlias,
                       @PathVariable("key") K key) {
    return cacheService.deleteEntry(cacheManagerName, cacheAlias, key);
  }

}