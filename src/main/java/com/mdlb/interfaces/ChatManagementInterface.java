package com.mdlb.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.mdlb.DTOs.LoginResponse;

public interface ChatManagementInterface extends Remote {
  public LoginResponse login(String username, String password) throws RemoteException;

  public boolean register(String username, String password, String confirmPassword) throws RemoteException;

  public boolean logout() throws RemoteException;

  public boolean sendMessage() throws RemoteException;

  public boolean receivedMessage() throws RemoteException;

  public boolean readMessage() throws RemoteException;

  public boolean sendBroadcastMessage() throws RemoteException;
}
