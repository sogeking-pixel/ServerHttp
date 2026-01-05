package Enum;

public enum HttpStatusCode {

    OK(200, "OK"),
    CREATED(201, "Creado"),

    BAD_REQUEST(400, "Error cliente"),
    NOT_FOUND(404, "No existe"),
    NOT_ALLOWED(405, "Metodo no existe"),
    INTERNAL_SERVER_ERROR(500, "Error en el servidor :("),
    NOT_IMPLEMENTED(501, "NO ESTA IMPLEMENTADO");


    private final int code;
    private final String displayName;

    HttpStatusCode(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static HttpStatusCode fromCode(int code) {
        for (HttpStatusCode status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código HTTP no válido: " + code);
    }
}
