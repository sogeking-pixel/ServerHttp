/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yerso
 */
public class Header {
     Map<String, String> dictionary =  new HashMap<>();

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }
    
    public void setValueDictionary(String key, String value){
        dictionary.put(key, value);
    }
    
    @Override
    public String toString(){
        return RecorrerHeader(dictionary);
    }
    
    private String RecorrerHeader(Map<String, String> dictionary) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            sb.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\r\n");

        }        
        return sb.toString();
    }
}
