package com.mdlb.interfaces;

import java.rmi.RemoteException;

public interface ChatManagementInterface {
  public boolean login() throws RemoteException;

  public boolean register() throws RemoteException;

  public boolean logout() throws RemoteException;

  public boolean sendMessage() throws RemoteException;

  public boolean receivedMessage() throws RemoteException;

  public boolean readMessage() throws RemoteException;

  public boolean sendBroadcastMessage() throws RemoteException;
}
