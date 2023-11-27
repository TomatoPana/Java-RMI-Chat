package com.mdlb.server;

import com.mdlb.interfaces.ChatManagementInterface;
import com.mdlb.DTOs.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.Random;
import java.util.ArrayList;
import com.mdlb.DTOs.LoginResponse;
import com.mdlb.DTOs.RegisterResponse;

public class ServerManagement extends UnicastRemoteObject implements ChatManagementInterface {

  private ArrayList<User> users;

  private String ip;

  private String port;

  public ServerManagement() throws RemoteException {
    super();

    this.users = new ArrayList<>();
    users.add(new User("test", "test"));
    users.add(new User("mdlb", "123456"));
    users.add(new User("root", "123456"));
  }

  public ServerManagement setIp(String ip) {
    this.ip = ip;
    return this;
  }

  public ServerManagement setPort(String port) {
    this.port = port;
    return this;
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
      if (u.getUsername().equals(user) && u.getPassword().equals(password)) {
        if (u.isOnline()) {
          return new LoginResponse(false, "The user already has an active session", null);
        }

        String token = this.generateToken();
        LoginResponse response = new LoginResponse(true, "Login successful", token);
        u.setToken(token);

        return response;
      }
    }

    return new LoginResponse(false, "Username/Password Incorrect", null);
  }

  /**
   * Generate a random string of 16 characters.
   * 
   * @return a random string of 16 characters.
   */
  public String generateToken() {
    String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder result = new StringBuilder(16);
    Random random = new Random();
    for (int i = 0; i < 16; i++) {
      int index = random.nextInt(source.length());
      result.append(source.charAt(index));
    }
    String randomString = result.toString();
    return randomString;
  }

  /**
   * Register a new user in the chat server.
   * 
   * @return true if the registration was successful, false otherwise.
   * @throws RemoteException
   */
  public RegisterResponse register(String username, String password, String confirmPassword) throws RemoteException {
    // Check if the username is already taken.
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return new RegisterResponse(false, "The username already exists.");
      }
    }

    // Check if the password and confirm password match.
    if (!password.equals(confirmPassword)) {
      return new RegisterResponse(false, "The password doesnt match.");
    }

    // Add the user to the list of users.
    users.add(new User(username, password));
    return new RegisterResponse(true, "Registration successful.");
  }

  /**
   * Verify if a token is a valid token
   * 
   * @param token The token
   * @return true if the token is valid, false otherwise.
   */
  public boolean validateToken(String token) {
    for (User u : users) {
      if (u.getToken().equals(token)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Attempt to logout from the chat server.
   * 
   * @param user The token of the user.
   * @return true if the logout was successful, false otherwise.
   * @throws RemoteException
   */
  public boolean logout(User user) throws RemoteException {
    // Check if the token is valid.
    if (!this.validateToken(user.getToken())) {
      return false;
    }

    user.setOnline(false);

    return true;
  }

  /**
   * Attempt to send a message to the chat server.
   * 
   * @return true if the message was sent successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean sendMessage(User user) throws RemoteException {
    // Check if the token is valid.
    if (!this.validateToken(user.getToken())) {
      return false;
    }

    return true;
  }

  /**
   * Mark a message as received.
   * 
   * @return true if the message was marked as received successfully, false
   *         otherwise.
   * @throws RemoteException
   */
  public boolean receivedMessage(User user) throws RemoteException {
    // Check if the token is valid.
    if (!this.validateToken(user.getToken())) {
      return false;
    }
    return true;
  }

  /**
   * Mark a message as read.
   * 
   * @return true if the message was marked as read successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean readMessage(User user) throws RemoteException {
    // Check if the token is valid.
    if (!this.validateToken(user.getToken())) {
      return false;
    }
    return true;
  }

  /**
   * Send a broadcast message to all users.
   * 
   * @return true if the broadcast message was sent successfully, false otherwise.
   * @throws RemoteException
   */
  public boolean sendBroadcastMessage(User user) throws RemoteException {
    // Check if the token is valid.
    if (!this.validateToken(user.getToken())) {
      return false;
    }
    return true;
  }

  public void start() throws Exception {
    LocateRegistry.createRegistry(Integer.parseInt(this.port));
    ChatManagementInterface chatManagement = this;
    java.rmi.Naming.rebind("rmi://" + this.ip + ":" + this.port + "/ChatManagement", chatManagement);
  }
}
