package br.com.guilhermenogueira.loja.loja.models;

public class ResponseException {
    private String message;
    private String status;
    private String method;

    public ResponseException() {
    }

    public ResponseException(String message, String status, String method) {
        this.message = message;
        this.status = status;
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getMethod() {
        return method;
    }
}
