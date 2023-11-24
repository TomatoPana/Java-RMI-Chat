package com.mdlb.DTOs;

public class RegisterResponse implements java.io.Serializable {
  private boolean success;
  private String message;

  public RegisterResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean getSuccess() {
    return this.success;
  }

  public String getMessage() {
    return this.message;
  }
}
