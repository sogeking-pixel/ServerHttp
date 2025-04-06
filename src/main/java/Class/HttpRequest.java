/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author yerso
 */
public class HttpRequest extends HttpMessages  {
    private StartLinerRequest startLine;
    
    public HttpRequest(String text) {
        try{
            String startline =GetStartLine(text);
            if (startline == null){
                throw new IllegalArgumentException("Error: No se puedo obtener el startline");
            }
            ParserStarline(startline);
        }
        catch(Exception e){
            System.out.println("Error parsing HTTP request: " + e.getMessage());
        }
    }
    
    private void ParserStarline(String firstLine) {
        System.out.println("No se que poner aca xd" + firstLine);
        String[] parts = firstLine.split(" ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Error: HTTP StarLine Incorrect");
        }
        
        HttpMethod method = HttpMethod.valueOf( parts[0]);
        String request = parts[1];     
        String protocol = parts[2];    
        System.out.println("wea supuestamente partida"+method+request+protocol);
        this.startLine = new StartLinerRequest(method, request, protocol);
    }
    
    public StartLinerRequest getStartLine() {
        return startLine;
    }
    
    
}

