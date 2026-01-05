/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author yerso
 */
public class Body {
    private byte[] content;

    public Body() {
        this.content = new byte[0];
    }

    public Body(byte[] content) {
        this.content = content;
    }

    public byte[] getContentRaw(){
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    @Override
    public String toString(){
        return new String(content, StandardCharsets.UTF_8);
    }


}
