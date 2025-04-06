package Class;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerHttp {
    /**
    *Estara en bucle, siempre escuchando
    *Los separadores del formato sera esto:
    *@linkRecurse https://mdn.github.io/shared-assets/images/diagrams/http/messages/http-message-anatomy.svg
   */
    
    private final String server = "localhost";
    private int port = 9876;
    private Socket socket;
    private final Map<String, List<String>> allowedRoutes = new HashMap<>();
    
    /*
        serverSocket = sirve para esperar y escuchar conexiones
        socket = sirve para la conexion del cliente y el servidor
    */

    public ServerHttp(){
         initUrls();
    }
    public ServerHttp(int port){
        this.port = port;
        initUrls();
    }
    
   
    
    private void initUrls(){
        this.allowedRoutes.put("/", List.of("GET"));
        this.allowedRoutes.put("/hola", List.of("GET", "POST"));
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
        
        String messageReceived = listenClient();
        
        HttpResponse response = handleRequest(messageReceived);
        
        responseClient(response);
        
    }
    
    private String listenClient() throws IOException{
        
        InputStream input = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = input.read(buffer);
        
        if(bytesRead == -1){
            throw  new ConnectException("the client did not send any message");
        }
        
        String messageReceived = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        
        return  messageReceived;
        
    }
    
    private void responseClient(HttpResponse response) throws IOException{
        
        OutputStream output = socket.getOutputStream();
         
        byte[] respuestaBytes = response.toString().getBytes();
        output.write(respuestaBytes);
         
    }
    
    private HttpResponse handleRequest(String messageReceived) throws IOException{
        /*
         intentar si existe el codigo, la url y manejar la respuesta
        */
        HttpRequest request = new HttpRequest(messageReceived);
        StartLinerRequest startline = request.getStartLine();
        
        System.out.println(startline.toString());
        
        String method = startline.getMethod().name();
        String url = startline.getRequestTarget();
        EnumProcotolAccess protocol = startline.getProtocol();
        
        
        System.out.println(this.allowedRoutes);
        System.out.println("wea: " + url);
        
        // hacer todo la viana de verificar las rutas y esas huevas
        if (! allowedRoutes.containsKey(url)){ return new HttpResponse(protocol, HttpStatusCode.NOT_FOUND);}
        
        if (! allowedRoutes.get(url).contains(method) ) return new HttpResponse(protocol, HttpStatusCode.NOT_ALLOWED);
        
        HttpResponse response =  new HttpResponse(protocol, HttpStatusCode.OK);
        
        String contentHtml = getHtmlToString("C:\\Users\\yerso\\OneDrive\\Documentos\\NetBeansProjects\\ServerHttp\\src\\main\\java\\Recurse\\index.html");
        
        response.getHeader().setValueDictionary("Content-Type", "text/html; charset=UTF-8");
        response.getBody().setContent(contentHtml);
        
        System.out.println("Done!");
        
        return response;
        
    }
    
    
    private String getHtmlToString(String path) throws IOException{
        byte[] fileBytes = Files.readAllBytes(Paths.get(path));
            
        String contentHtml = new String(fileBytes, StandardCharsets.UTF_8);
        return contentHtml;
    }

}

