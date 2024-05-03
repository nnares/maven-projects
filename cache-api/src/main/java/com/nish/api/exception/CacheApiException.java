package com.nish.api.exception;

public class CacheApiException extends RuntimeException {
  private static final long serialVersionUID = -9083647126141368734L;

  public CacheApiException(Throwable cause) {
    super(cause);
  }

  public CacheApiException(String message) {
    super(message);
  }

  public CacheApiException(String message, Throwable cause) {
    super(message, cause);
  }
}
