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
    private int statusCode;
    private String statusText = "";

     public StartLineResponse(String protocol, int statusCode, String statusText) {
         this.protocol = protocol;
         this.statusCode = statusCode;
         this.statusText = statusText;
     }

    @Override
     public String toString(){
         return protocol + " " + statusCode + " " + statusText + "\r\n";
     }       

    @Override
    public String getProtocol() {
        return protocol;
    }
}
