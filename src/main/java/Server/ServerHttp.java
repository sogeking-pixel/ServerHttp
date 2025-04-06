/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Server;

import Class.HttpResponse;
import Class.HttpRequest;
import Class.StartLinerRequest;
import Class.HttpStatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static int port = 5922;
    private static String server = "localhost";

    public static void main(String[] args) throws IOException, IllegalAccessException{
        /**
         *Estara en bucle, siempre escuchando
         *Los separadores del formato sera esto:
         *@linkRecurse https://mdn.github.io/shared-assets/images/diagrams/http/messages/http-message-anatomy.svg
        */

        
        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\yerso\\OneDrive\\Documentos\\NetBeansProjects\\ServerHttp\\src\\main\\java\\Recurse\\index.html"));
            
        String contentHtml = new String(fileBytes, StandardCharsets.UTF_8);

     
        HttpResponse response_200 = new HttpResponse("HTTP/1.1", HttpStatusCode.OK);
        
        HttpResponse response_404 = new HttpResponse("HTTP/1.1", HttpStatusCode.NOT_FOUND);
        
        
        HttpResponse get_page = new HttpResponse(response_200);
        get_page.getHeader().setValueDictionary("Content-Type", "text/html; charset=UTF-8");

        get_page.getBody().setContent(contentHtml);
        
        try {
           ServerSocket serverSocket = new ServerSocket(port);
           while(true){     
                System.out.println("Wait client for port: " + port + "...");

                Socket socket = serverSocket.accept();

                System.out.println("Cliente Conected: " + socket.getInetAddress());                
                
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer); 
                
                if (bytesRead == -1){
                    throw new IllegalAccessException("no se recibio ningun mensaje del cliente");
                }
                
                String mensajeRecibido = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                
//                System.out.println("Cliente dice: " + mensajeRecibido);
                HttpRequest request = new HttpRequest(mensajeRecibido);
                
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
