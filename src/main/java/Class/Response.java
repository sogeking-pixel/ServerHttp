/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author yerso
 */
public class Response extends HttpMessages {
    private StartLineResponse starLine;

    public Response(String protocol, int statusCode, String statusText) {
        this.starLine = new StartLineResponse(protocol, statusCode, statusText);
    }

    public StartLineResponse getStarLine() {
        return starLine;
    }

    public void setStarLine(StartLineResponse starLine) {
        this.starLine = starLine;
    }

    
   
    @Override
   public String toString(){
       return starLine + "\r\n";
   }
   
    
}


