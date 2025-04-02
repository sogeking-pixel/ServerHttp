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
import java.nio.file.Files;
import java.nio.file.Paths;
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
        
        int port = 5676;
        
        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\yerso\\OneDrive\\Documentos\\NetBeansProjects\\ServerHttp\\src\\main\\java\\Recurse\\index.html"));
            
        String contenidoHtml = new String(fileBytes, StandardCharsets.UTF_8);

     
        Response response_200 = new Response("HTTP/1.1", 200, "OKI DOKI");
        
        Response response_404 = new Response("HTTP/1.1", 404, "Not Found");
        
        
        Response get_page = new Response(response_200);
        get_page.getHeader().setValueDictionary("Content-Type", "text/html; charset=UTF-8");

        get_page.getBody().setContent(contenidoHtml);
        
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
                
//                System.out.println("Cliente dice: " + mensajeRecibido);
                Resquest request = new Resquest(mensajeRecibido);
                
                StartLinerRequest startline = request.getStartLine();
                
                if(startline == null){
                    throw new IllegalArgumentException("Error: startline null");
                }
                String url = startline.getRequestTarget();
                
                if(url == null){
                    throw new IllegalArgumentException("Error: url a que acceder, null");
                }
                
                String respuesta;
                
                if (url.equals("/index")){
                    
                    respuesta =  get_page.toString();
                    System.out.println("Se envio correctamente");
                }
                else{
                    respuesta =  response_404.toString();
                    System.out.println("Pagina no existe");

                }
                
                
                byte[] respuestaBytes = respuesta.getBytes();
                output.write(respuestaBytes);
                
                System.out.println(respuesta);
                

                // Cerrar conexión
                socket.close();
                System.out.println("Conexion cerrada.");
                
           }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    
    
}
