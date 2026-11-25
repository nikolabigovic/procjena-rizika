package com.nb.procjena_rizika.exception;

public class NepostojecaSifraDjelatnostiException extends RuntimeException {
    public NepostojecaSifraDjelatnostiException(String message) {
        super("Unesena sifra djelatnosti ne postoji!");
    }
}
