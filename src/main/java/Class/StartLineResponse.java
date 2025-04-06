/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Interfaz.IStartLine;

/**
 *
 * @author yerso
 */
public class StartLineResponse implements IStartLine{
    private String protocol = "";
    private HttpStatusCode statusCode;

     public StartLineResponse(String protocol, HttpStatusCode statusCode) {
         this.protocol = protocol;
         this.statusCode = statusCode;
     }

    @Override
     public String toString(){
         return protocol + " " + statusCode.getCode() + " " + statusCode.getDisplayName() + "\r\n";
     }

    @Override
    public String getProtocol() {
        return protocol;
    }
}
