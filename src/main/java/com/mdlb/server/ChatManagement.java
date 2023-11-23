package com.mdlb.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.mdlb.interfaces.ChatManagementInterface;

public class ChatManagement extends UnicastRemoteObject implements ChatManagementInterface {

  protected ChatManagement() throws RemoteException {
    super();
  }

  /**
   * Attempt to login to the chat server.
   * 
   * @return true if the login was successful, false otherwise.
   * @throws RemoteException
   */
  public boolean login() throws RemoteException {

    return true;
  }

  /**
   * Register a new user in the chat server.
   * 
   * @return true if the registration was successful, false otherwise.
   * @throws RemoteException
   */
  public boolean register() throws RemoteException {

    return true;
  }

  /**
   * Attempt to logout from the chat server.
   * 
   * @return true if the logout was successful, false otherwise.
   * @throws RemoteException
   */
  public boolean logout() throws RemoteException {

    return true;
  }

  /**
   * Attempt to send a message to the chat server.
   * 
   * @return true if the message was sent successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean sendMessage() throws RemoteException {

    return true;
  }

  /**
   * Mark a message as received.
   * 
   * @return true if the message was marked as received successfully, false
   *         otherwise.
   * @throws RemoteException
   */
  public boolean receivedMessage() throws RemoteException {

    return true;
  }

  /**
   * Mark a message as read.
   * 
   * @return true if the message was marked as read successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean readMessage() throws RemoteException {

    return true;
  }

  /**
   * Send a broadcast message to all users.
   * 
   * @return true if the broadcast message was sent successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean sendBroadcastMessage() throws RemoteException {

    return true;
  }

  public static void main(String[] args) {

  }

}
