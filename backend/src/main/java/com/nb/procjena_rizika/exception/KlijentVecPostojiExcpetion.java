package com.nb.procjena_rizika.exception;

public class KlijentVecPostojiExcpetion extends RuntimeException {
    public KlijentVecPostojiExcpetion(String message) {
        super("Klijent sa tim PIB-om je vec registrovan");
    }
}
