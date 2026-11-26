package com.nb.procjena_rizika.exception;

public class ProvjeraVlasnistvaException extends RuntimeException {
    public ProvjeraVlasnistvaException(String message) {
        super("Ova stavka ne pripada vasoj firmi!");
    }
}
