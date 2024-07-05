package com.backend.curso.Excepciones;

public class AuthenticationFailException extends Exception {
    private String path;

    public AuthenticationFailException(String msg) {
        super(msg);
    }

    public AuthenticationFailException(String msg, String path) {
        super(msg);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

