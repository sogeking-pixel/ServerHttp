package Class;

import java.io.ByteArrayOutputStream;
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
import Enum.EnumProcotolAccess;
import Enum.HttpStatusCode;


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
    private final ExecutorService virtualThread;
;

    
    /*
        serverSocket = sirve para esperar y escuchar conexiones
        socket = sirve para la conexion del cliente y el servidor
    */

    public ServerHttp(){
        this.virtualThread = Executors.newVirtualThreadPerTaskExecutor();
        initUrls();
    }
    public ServerHttp(String ipLocal,int port){
        this.port = port;
        this.virtualThread = Executors.newVirtualThreadPerTaskExecutor();
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

            virtualThread.submit(new ClientHandler(clientSocket, this.allowedRoutes));
            
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

                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();


                while (!socket.isClosed()) {

                    String headerString = readHeadersRobust(input);

                    if (headerString == null || headerString.isEmpty()) {
                        break;
                    }

                    HttpResponse response = handleRequest(headerString);

                    response.send(output);

                }
                
            } catch (IOException ex) {
                Logger.getLogger(ServerHttp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Client closed: " + socket.getInetAddress());
            }

            
        }

        private String readHeadersRobust(InputStream input) throws IOException {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int b;
            int state = 0;

            while ((b = input.read()) != -1) {
                buffer.write(b);

                if (b == '\r' && (state == 0 || state == 2)) state++;
                else if (b == '\n' && (state == 1 || state == 3)) state++;
                else state = 0;

                if (state == 4) {
                    return buffer.toString(StandardCharsets.UTF_8);
                }
            }
            return null;
        }

        
        private HttpResponse handleRequest(String messageReceived) throws IOException{

            HttpRequest request = new HttpRequest(messageReceived);
            StartLinerRequest startline = request.getStartLine();

            System.out.println(startline.toString());

            String method = startline.getMethod().name();
            String url = startline.getRequestTarget();
            EnumProcotolAccess protocol = startline.getProtocol();


            System.out.println(this.allowedRoutes);
            System.out.println("wea: " + url);

            if (! allowedRoutes.containsKey(url)){ return new HttpResponse(protocol, HttpStatusCode.NOT_FOUND);}

            if (! allowedRoutes.get(url).contains(method) ) return new HttpResponse(protocol, HttpStatusCode.NOT_ALLOWED);

            HttpResponse response =  new HttpResponse(protocol, HttpStatusCode.OK);
            byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\yerso\\OneDrive\\Documentos\\NetBeansProjects\\ServerHttp\\src\\main\\java\\Recurse\\index.html"));
            response.getHeader().setValueDictionary("Content-Length", String.valueOf(fileBytes.length));
            response.getHeader().setValueDictionary("Content-Type", "text/html; charset=UTF-8");
            response.getBody().setContent(fileBytes);

            System.out.println("Done!");

            return response;

        }
        
    }

}

