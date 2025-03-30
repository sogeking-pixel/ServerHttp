/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serverhttp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author yerso
 */
public class ServerHttp {

    public static void main(String[] args) throws IOException{
        int port = 9999;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Wait client for port: " + port + "...");
            
            Socket socket = serverSocket.accept();
            System.out.println("Cliente Conected: " + socket.getInetAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
