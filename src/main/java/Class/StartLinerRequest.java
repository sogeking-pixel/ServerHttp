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

    private final HttpMethod method;
    private final String requestTarget;
    private final EnumProcotolAccess protocol;

    public StartLinerRequest(HttpMethod method, String requestTarget, EnumProcotolAccess protocol){
        this.method = method;
        this.requestTarget = requestTarget;
        this.protocol = protocol;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }
    
    @Override
    public EnumProcotolAccess getProtocol() {
        return protocol;
    }

    @Override
    public String toString(){
        return method + " " + requestTarget + " " + protocol + "\r\n";
    }


}
