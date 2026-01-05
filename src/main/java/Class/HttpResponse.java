/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Enum.HttpStatusCode;
import Enum.EnumProcotolAccess;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author yerso
 */

public class HttpResponse extends HttpMessages {
    private StartLineResponse starLine;
    private Header header;
    private Body body;

    public HttpResponse(EnumProcotolAccess protocol, HttpStatusCode statusCode) {
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

   
    public void send(OutputStream output) throws IOException{
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append(this.starLine.toString());
        headerBuilder.append(this.header.toString());
        headerBuilder.append("\r\n");

        byte[] headerBytes = headerBuilder.toString().getBytes(StandardCharsets.US_ASCII);
        byte[] bodyBytes = this.body.getContentRaw();

        output.write(headerBytes);

        if (bodyBytes != null && bodyBytes.length > 0) {
            output.write(bodyBytes);
        }

        output.flush();
    }
   
    
}


