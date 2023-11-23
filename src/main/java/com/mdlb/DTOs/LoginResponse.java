package com.mdlb.DTOs;

import java.io.Serializable;

public class LoginResponse implements Serializable {
  private boolean success;
  private String message;
  private String token;

  public LoginResponse(boolean success, @Nullable String message, @Nullable String token) {
    this.success = success;
    this.message = message;
    this.token = token;
  }

  public boolean getSuccess() {
    return this.success;
  }

  public String getMessage() {
    return this.message;
  }

  public String getToken() {
    return this.token;
  }
}
