/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.Map;

/**
 *
 * @author yerso
 */
public class BodyResponse {
    private String content;

    public BodyResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    @Override
    public String toString(){
        return content;
    }
    
   
}
