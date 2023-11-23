package com.mdlb;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.util.Scanner;

import com.mdlb.DTOs.LoginResponse;
import com.mdlb.interfaces.ChatManagementInterface;

public class Client {
  public static void main(String[] args) throws InterruptedException {
    boolean retry = false;
    do {
      try {
        ChatManagementInterface RMI = (ChatManagementInterface) Naming.lookup("//127.0.0.1:1099/ChatManagement");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Password:");
        String password = scanner.next();

        LoginResponse response = RMI.login(username, password);

        if (response.getSuccess()) {
          System.out.println("Bienvenido " + response.getMessage());
        } else {
          System.out.println("Error: " + response.getMessage());
        }

        scanner.close();

        System.out.println("Invocación remota exitosa");
        retry = false;
      } catch (ConnectException e) {
        System.err.println("Conexión rechazada, reintentando en 5 segundos...");
        Thread.sleep(5000);
        retry = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    } while (retry == true);
  }
}
