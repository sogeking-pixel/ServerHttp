/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Server;

import Class.EnumMethod;
import Class.Response;
import Class.Resquest;
import Class.StartLineResponse;
import Class.StartLinerRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author yerso
 */
public class ServerHttp {

    public static void main(String[] args) throws IOException, IllegalAccessException{
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
        Response response_200 = new Response("HTTP/1.1", 200, "OKI DOKI");
        Response response_404 = new Response("HTTP/1.1", 404, "Not Found");

        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
           while(true){     
                System.out.println("Wait client for port: " + port + "...");

                Socket socket = serverSocket.accept();

                System.out.println("Cliente Conected: " + socket.getInetAddress());                
                
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                
                byte[] buffer = new byte[1024]; // Ajusta el tamaño según sea necesario
                int bytesRead = input.read(buffer); 
                
                if (bytesRead == -1){
                    throw new IllegalAccessException("no se recibio ningun mensaje del cliente");
                }
                
                String mensajeRecibido = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                
                System.out.println("Cliente dice: " + mensajeRecibido);
                Resquest request = new Resquest(mensajeRecibido);
                
                StartLinerRequest startline = request.getStartLine();
                
                if(startline == null){
                    throw new IllegalArgumentException("Error: Null start line xddxdxdxdxdx");
                }
                String url = startline.getRequestTarget();
                
                if(url == null){
                    throw new IllegalArgumentException("Error: Null xddxdxdxdxdx");
                }
                
                String respuesta;
                
                if (url.equals("/index")){
                    respuesta =  response_200.toString();
                }
                else{
                    respuesta =  response_404.toString();
                }
                
                
                byte[] respuestaBytes = respuesta.getBytes();
                output.write(respuestaBytes);

                // Cerrar conexión
                socket.close();
                System.out.println("Conexion cerrada.");
                
           }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    
    
}
