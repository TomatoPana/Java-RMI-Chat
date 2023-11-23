package com.mdlb.DTOs;

public class User {
  private String username;
  private String password;
  private String ip;
  private int port;

  public User(String username, String password, @Nullable String ip, int port) {
    this.username = username;
    this.password = password;
    this.ip = ip;
    this.port = port;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.ip = null;
    this.port = 0;
  }

  public User() {
    this.username = null;
    this.password = null;
    this.ip = null;
    this.port = 0;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getIp() {
    return this.ip;
  }

  public int getPort() {
    return this.port;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
