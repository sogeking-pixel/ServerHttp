/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author yerso
 */
public enum EnumProcotolAccess {
    HTTP_1_1("HTTP/1.1");
    
    private final String typeVersion;

    EnumProcotolAccess(String typeVersion) {
        this.typeVersion = typeVersion;
    }

    

    public String getTypeVersion() {
        return typeVersion;
    }
    
    public static EnumProcotolAccess fromTypeVersion(String version) {
        for (EnumProcotolAccess e : EnumProcotolAccess.values()) {
            if (e.getTypeVersion().equals(version)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Version is not support: " + version);
    }
}
