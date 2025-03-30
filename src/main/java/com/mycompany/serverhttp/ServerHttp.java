/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serverhttp;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author yerso
 */
public class ServerHttp {

    public static void main(String[] args) throws IOException{
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8088), 0);
       
        server.start();
        System.out.println("Servidor iniciado en el puerto 8088");
    }
}
