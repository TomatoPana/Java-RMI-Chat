package com.mdlb.server;

import com.mdlb.interfaces.ChatManagementInterface;
import com.mdlb.DTOs.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.ArrayList;
import com.mdlb.DTOs.LoginResponse;

public class ChatManagement extends UnicastRemoteObject implements ChatManagementInterface {

  private ArrayList<User> users;

  protected ChatManagement() throws RemoteException {
    super();

    this.users = new ArrayList<>();
    users.add(new User("test", "test"));
  }

  /**
   * Attempt to login to the chat server.
   * 
   * @return true if the login was successful, false otherwise.
   * @throws RemoteException
   */
  public LoginResponse login(String user, String password) throws RemoteException {
    // Iterate through users and check if the user exists and the password is
    // correct. Also check if the user is already logged in.
    for (User u : users) {
      if (u.getUsername().equals(user) && u.getPassword().equals(password) && !u.isOnline()) {
        return new LoginResponse(true, "Login successful", "token");
      }
    }

    return new LoginResponse(false, "Login failed", null);
  }

  /**
   * Register a new user in the chat server.
   * 
   * @return true if the registration was successful, false otherwise.
   * @throws RemoteException
   */
  public boolean register(String username, String password, String confirmPassword) throws RemoteException {
    // Check if the username is already taken.
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return false;
      }
    }

    // Check if the password and confirm password match.
    if (!password.equals(confirmPassword)) {
      return false;
    }

    // Add the user to the list of users.
    return users.add(new User(username, password));
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

  private static ArrayList<String> getLocalAddress() {
    ArrayList<String> foundAddresses = new ArrayList<String>();
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();
        if (!networkInterface.isUp())
          continue;
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
          InetAddress address = addresses.nextElement();
          if (address.isLoopbackAddress())
            continue;
          if (address.isSiteLocalAddress())
            foundAddresses.add(address.getHostAddress());
        }
      }
    } catch (SocketException e) {
      System.err.println(
          "Error getting network interfaces. Either the host is not connected to a network or the network is down.");
      e.printStackTrace();
    }
    return foundAddresses;
  }

  public static void main(String[] args) {
    ArrayList<String> addresses = getLocalAddress();

    if (addresses.size() == 0) {
      System.err.println("No local addresses found. Exiting...");
      System.exit(1);
    }

    // String address = addresses.get(0);
    String address = "127.0.0.1";
    int port = 1099;

    try {
      LocateRegistry.createRegistry(port);
      ChatManagementInterface chatManagement = new ChatManagement();
      java.rmi.Naming.rebind("rmi://" + address + ":" + port + "/ChatManagement", chatManagement);
      System.out.println("ChatManagement bound in registry at rmi://" + address + ":" + port + "/ChatManagement");
    } catch (Exception e) {
      System.err.println("Error parsing arguments. Exiting...");
      e.printStackTrace();
      System.exit(1);
    }

  }
}
