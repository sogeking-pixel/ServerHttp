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
            return "Error: Input vacío o nulo";
        }

        // Encontrar la primera línea del texto
        int indexStartLine = text.indexOf("\r\n");
        if (indexStartLine == -1) {
            return "Error: No se encontró la línea de inicio";
        }

        String firstLine = text.substring(0, indexStartLine).trim(); // Trim para evitar espacios extras

        // Separar los componentes de la línea usando split
        String[] parts = firstLine.split(" ");
        if (parts.length < 3) {
            return "Error: Formato de línea inválido";
        }

        String method = parts[0];      // Método (GET, POST, etc.)
        String request = parts[1];     // Ruta solicitada
        String protocol = parts[2];    // Protocolo (HTTP/1.1)

        return "Method: " + method + " | Request: " + request + " | Protocol: " + protocol;
    }

    
    public static String GetHeader(String text){
        if (text == null || text.isEmpty()){
            return "nose";
        }
        
        int indexHeaderStart = text.indexOf("\r\n");
        int indexHeaderEnd = text.indexOf("\r\n\r\n");
        if (indexHeaderEnd == -1){
            return "pene";
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
        
        return recorrerNose(dictionary);
        
    }
    
    public static String GetBody(String text){
        if (text == null || text.isEmpty()){
            return "nose";
        }        
        
        int indexBodyEnd = text.indexOf("\r\n\r\n");
        if (indexBodyEnd == -1){
            return "No existe mi pene";
        }
        
        var body =  text.substring(indexBodyEnd).trim();
        
        
        return body;
    }
    
    private static String recorrerNose(Map<String, String> dictionary) {
        String cadena = "";
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            cadena += "\n" + entry.getKey() + ": " + entry.getValue() ;
        }        
        return cadena;
    }
}
