/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Enum.HttpMethod;
import Enum.EnumProcotolAccess;
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
        
        String[] parts = firstLine.split(" ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Error: HTTP StarLine Incorrect");
        }
        
        HttpMethod method = HttpMethod.valueOf( parts[0]);
        String url = parts[1];     
        EnumProcotolAccess protocol = EnumProcotolAccess.fromTypeVersion( parts[2]);    
       
        this.startLine = new StartLinerRequest(method, url, protocol);
    }
    
    public StartLinerRequest getStartLine() {
        return startLine;
    }
    
    
}

