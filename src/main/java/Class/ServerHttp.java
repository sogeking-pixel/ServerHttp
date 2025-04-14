package Class;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHttp{
    /**
    *Estara en bucle, siempre escuchando
    *Los separadores del formato sera esto:
    *@linkRecurse https://mdn.github.io/shared-assets/images/diagrams/http/messages/http-message-anatomy.svg
   */
    
    private final String server = "localhost";
    private String ipLocal = "127.0.0.0"; 
    private int port = 9876;
    private final Map<String, List<String>> allowedRoutes = new HashMap<>();
    private final ExecutorService threadPool;
;

    
    /*
        serverSocket = sirve para esperar y escuchar conexiones
        socket = sirve para la conexion del cliente y el servidor
    */

    public ServerHttp(int maxThreads){
        this.threadPool = Executors.newFixedThreadPool(maxThreads);
        initUrls();
    }
    public ServerHttp(String ipLocal,int port, int maxThreads){
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(maxThreads);
        this.ipLocal = ipLocal;

        initUrls();
    }
    
   
    
    private void initUrls(){
        this.allowedRoutes.put("/", List.of("GET"));
        this.allowedRoutes.put("/hola", List.of("GET", "POST"));
    }
        
    
    
    public void start() throws IOException{
        
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ipLocal, port));
        while(true){
            System.out.println("Wait client for port: " + port + "...");
            Socket clientSocket = serverSocket.accept();
            
            threadPool.submit(new ClientHandler(clientSocket, this.allowedRoutes));           
            
        }

    }
    
       
    
    
    
    
    
    
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private final Map<String, List<String>> allowedRoutes;

        public ClientHandler(Socket socket, Map<String, List<String>> allowedRoutes) {
            this.socket = socket;
            this.allowedRoutes = allowedRoutes;
        }       
        
        
        
         @Override
        public void run()  {
            
            System.out.println("Client conected: " + socket.getInetAddress());
                        
            try {
                String messageReceived;
                messageReceived = listenClient();
                HttpResponse response = handleRequest(messageReceived);

                responseClient(response);
                
                this.socket.close();
                System.out.println("Conexion Closed.\n\n");
                
            } catch (IOException ex) {
                Logger.getLogger(ServerHttp.class.getName()).log(Level.SEVERE, null, ex);
            }

            
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
        
        
        private void responseClient(HttpResponse response) throws IOException{
        
            OutputStream output = socket.getOutputStream();

            byte[] respuestaBytes = response.toString().getBytes();
            output.write(respuestaBytes);
            this.socket.close();

        }        
        
        
        
        
    }

}

