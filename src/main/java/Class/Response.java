/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.Objects;

/**
 *
 * @author yerso
 */
public class Response extends HttpMessages {
    private StartLine starLine;

    public Response(String protocol, int statusCode, String statusText) {
        this.starLine = new StartLine(protocol, statusCode, statusText);
    }

    public StartLine getStarLine() {
        return starLine;
    }

    public void setStarLine(StartLine starLine) {
        this.starLine = starLine;
    }

    
   
    @Override
   public String toString(){
       return starLine + "\r\n";
   }
   
   class StartLine{
       private String protocol = "";
       private int statusCode;
       private String statusText = "";

        public StartLine(String protocol, int statusCode, String statusText) {
            this.protocol = protocol;
            this.statusCode = statusCode;
            this.statusText = statusText;
        }
       
       @Override
        public String toString(){
            return protocol + " " + statusCode + " " + statusText + "\r\n";
        }       
   }
   
   class Header{
   
   }
   
   class Body{
       
   }
   
    
    
    
}


