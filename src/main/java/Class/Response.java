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
    private HeaderResponse header;
    private BodyResponse body;

    public Response(String protocol, int statusCode, String statusText) {
        this.starLine = new StartLineResponse(protocol, statusCode, statusText);
        this.header = new HeaderResponse();
        this.body = new BodyResponse(null);
    }
    
    public Response(Response response){
        this.starLine = response.starLine;
        this.header = response.header;
        this.body = response.body;
    }
    
    public StartLineResponse getStarLine() {
        return starLine;
    }

    public void setStarLine(StartLineResponse starLine) {
        this.starLine = starLine;
    }

    public HeaderResponse getHeader() {
        return header;
    }

    public void setHeader(HeaderResponse header) {
        this.header = header;
    }

    public BodyResponse getBody() {
        return body;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }

    
    
   
    @Override
   public String toString(){
       return starLine.toString() + header.toString()  + "\r\n" + body.toString() ;
   }
   
    
}


