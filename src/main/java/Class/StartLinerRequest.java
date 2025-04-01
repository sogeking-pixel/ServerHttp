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

public class StartLinerRequest implements IStartLine{

    private final EnumMethod method;
    private final String requestTarget;
    private final String protocol;

    public StartLinerRequest(EnumMethod method, String requestTarget, String protocol){
        this.method = method;
        this.requestTarget = requestTarget;
        this.protocol = protocol;
    }

    public EnumMethod getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }
    
    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String toString(){
        return method + " " + requestTarget + " " + protocol + "\r\n";
    }


}
