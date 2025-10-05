package com.tourdesign.platform.exception;

import java.util.Set;

public class DataUnSupportedFieldPatchException extends RuntimeException{

    public DataUnSupportedFieldPatchException(Set<String> keys) {
        super("Campo "+keys.toString()+" no esta permitido");
    }

}