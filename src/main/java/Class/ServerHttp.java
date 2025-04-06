package Class;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerHttp {
    private final String server = "localhost";
    private int port = 9876;
    private Socket socket;
    
    /*
        serverSocket = sirve para esperar y escuchar conexiones
        socket = sirve para la conexion del cliente y el servidor
    */

    public void ServerHttp(int port){
        this.port = port;
    }
    
    public void ServerHttp(){
        
    }

    public void start() throws IOException{
        
        ServerSocket serverSocket = new ServerSocket(port);
        
        while(true){
            System.out.println("Wait client for port: " + port + "...");
            this.socket = serverSocket.accept();
            
            handleClient();
            
            this.socket.close();
            System.out.println("Conexion Closed.");
        }

    }
    
    private void handleClient() throws IOException{
        
        System.out.println("Client conected: " + socket.getInetAddress());
        
        HttpRequest resquest = listenedClient();
        
        handleRequest(resquest);
        
        responseClient();
        
    }
    
    private HttpRequest listenedClient() throws IOException{
        
        InputStream input = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = input.read(buffer);
        
        if(bytesRead == -1){
            throw  new ConnectException("the client did not send any message");
        }
        
        String messageReceived = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        
        return  new HttpRequest(messageReceived);
        
    }
    
    private void responseClient() throws IOException{
         OutputStream output = socket.getOutputStream();
    }
    
    private void handleRequest(HttpRequest resquest){
        
        StartLinerRequest startline = resquest.getStartLine();
        
        if (startline == null) throw new IllegalArgumentException("Error: startline null");
        String url = startline.getProtocol();
        
        
    }

}

