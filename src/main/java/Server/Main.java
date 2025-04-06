/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Server;


import Class.ServerHttp;
import java.io.IOException;

/**
 *
 * @author yerso
 */
public class Main {
    

    public static void main(String[] args) throws IOException, IllegalAccessException{
        
        
        ServerHttp serverHttp = new ServerHttp();
        serverHttp.start();
        
    }

 
    
    
}
