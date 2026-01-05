/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Interfaz.IStartLine;
import Enum.HttpStatusCode;
import Enum.EnumProcotolAccess;

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
        StringBuilder sb = new StringBuilder();
        sb.append(protocol.getTypeVersion())
                .append(" ")
                .append(statusCode.getCode())
                .append(" ")
                .append(statusCode.getDisplayName())
                .append("\r\n");

         return sb.toString();
     }

    @Override
    public EnumProcotolAccess getProtocol() {
        return protocol;
    }
}
