/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Server;

import Class.Response;
import Class.Resquest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author yerso
 */
public class ServerHttp {

    public static void main(String[] args) throws IOException{
        /*
            Estara en bucle, siempre escuchando
            Los separadores del formato sera esto:
            https://mdn.github.io/shared-assets/images/diagrams/http/messages/http-message-anatomy.svg
        */
        int port = 9999;
        
//        String request_text = "POST /users HTTP/1.1\r\n" +
//                              "Host: example.com\r\n" +
//                              "Content-Type: application/x-www-form-urlencoded\r\n" +
//                              "Content-Length: 5\r\n"+
//                              "\r\n" +
//                              "hola mundo, este es el maldito body xddxdx";
//        
//        System.out.println("First Line: " + Resquest.GetStartLine(request_text));
//        System.out.println("Headers: " + Resquest.GetHeader(request_text));
//        System.out.println("Body: " + Resquest.GetBody(request_text));
//
        Response response = new Response("HTTP/1.1", 200, "OKI DOKI");
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
           while(true){     
                System.out.println("Wait client for port: " + port + "...");

                Socket socket = serverSocket.accept();

                System.out.println("Cliente Conected: " + socket.getInetAddress());                
                
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String mensajeRecibido = reader.readLine();
                System.out.println("Cliente dice: " + mensajeRecibido);

                // Respuesta del servidor
                String respuesta =  response.toString();
                byte[] respuestaBytes = respuesta.getBytes(); // Convertir String a bytes
                output.write(respuestaBytes); // Enviar respuesta

                // Cerrar conexión
                socket.close();
                System.out.println("Conexion cerrada.");
                
           }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    
    
}
