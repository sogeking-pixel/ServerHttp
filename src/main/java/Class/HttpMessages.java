/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yerso
 */
public class HttpMessages {
    
    
    public static String GetStartLine(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        int indexStartLine = text.indexOf("\r\n");
        if (indexStartLine == -1) {
            return null;
        }
        
        String firstLine = text.substring(0, indexStartLine).trim(); 
        
        
        return firstLine;
        
    }

    
    public static String GetHeader(String text){
        if (text == null || text.isEmpty()){
            return null;
        }
        
        int indexHeaderStart = text.indexOf("\r\n");
        int indexHeaderEnd = text.indexOf("\r\n\r\n");
        if (indexHeaderEnd == -1){
            return null;
        }
        
        String header =  text.substring(indexHeaderStart, indexHeaderEnd).trim();
        String[] parts = header.split("\r\n");
        Map<String, String> dictionary =  new HashMap<>();
        
        for(String part : parts){
            String[] nosexd = part.split(":", 2);
            if (nosexd.length != 2) {
               continue;
            }
             dictionary.put(nosexd[0].trim(), nosexd[1].trim());
        }       
        
        return RecorrerHeader(dictionary);
        
    }
    
    public static String GetBody(String text){
        if (text == null || text.isEmpty()){
            return null;
        }        
        
        int indexBodyEnd = text.indexOf("\r\n\r\n");
        if (indexBodyEnd == -1){
            return null;
        }
        
        var body =  text.substring(indexBodyEnd).trim();
        
        
        return body;
    }

    protected static String RecorrerHeader(Map<String, String> dictionary) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            sb.append("\n")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        return sb.toString();
    }
}
