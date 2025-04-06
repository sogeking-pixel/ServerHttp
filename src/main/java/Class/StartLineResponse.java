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
    private final EnumProcotolAccess protocol;
    private final HttpStatusCode statusCode;

     public StartLineResponse(EnumProcotolAccess protocol, HttpStatusCode statusCode) {
         this.protocol = protocol;
         this.statusCode = statusCode;
     }

    @Override
     public String toString(){
         return protocol.getTypeVersion() + " " + statusCode.getCode() + " " + statusCode.getDisplayName() + "\r\n";
     }

    @Override
    public String getProtocol() {
        return protocol.getTypeVersion();
    }
}
