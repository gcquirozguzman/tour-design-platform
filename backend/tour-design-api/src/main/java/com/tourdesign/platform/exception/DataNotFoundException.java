package com.tourdesign.platform.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(Long id) {
        super("Registro no encontrado con id : "+id);
    }

    public DataNotFoundException(String dato) {
        super("Registro no encontrado dato : "+dato);
    }

}