/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author yerso
 */

public class HttpResponse extends HttpMessages {
    private StartLineResponse starLine;
    private Header header;
    private Body body;

    public HttpResponse(String protocol, HttpStatusCode statusCode) {
        this.starLine = new StartLineResponse(protocol, statusCode);
        this.header = new Header();
        this.body = new Body(null);
    }
    
    public HttpResponse(HttpResponse response){
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

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    
    
   
    @Override
   public String toString(){
        System.out.println("1." + starLine.toString() + "2." + header.toString()  + "\r\n" + "3." + body.toString());
        return starLine.toString() + header.toString()  + "\r\n" + body.toString() ;
   }
   
    
}


