package com.mdlb.DTOs;

public class ServerConfiguration {
  private String ip;
  private String port;

  public ServerConfiguration(String ip, String port) {
    this.ip = ip;
    this.port = port;
  }

  public String getIp() {
    return this.ip;
  }

  public String getPort() {
    return this.port;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getFormattedIp() {
    return this.ip + ":" + this.port;
  }
}
