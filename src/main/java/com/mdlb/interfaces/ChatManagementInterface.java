package com.mdlb.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.mdlb.DTOs.LoginResponse;
import com.mdlb.DTOs.RegisterResponse;
import com.mdlb.DTOs.User;

public interface ChatManagementInterface extends Remote {
  public LoginResponse login(String username, String password) throws RemoteException;

  public RegisterResponse register(String username, String password, String confirmPassword) throws RemoteException;

  public boolean logout(User user) throws RemoteException;

  public boolean sendMessage(User user) throws RemoteException;

  public boolean receivedMessage(User user) throws RemoteException;

  public boolean readMessage(User user) throws RemoteException;

  public boolean sendBroadcastMessage(User user) throws RemoteException;
}
