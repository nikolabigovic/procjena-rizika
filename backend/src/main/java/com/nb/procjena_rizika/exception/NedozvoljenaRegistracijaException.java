package com.nb.procjena_rizika.exception;

public class NedozvoljenaRegistracijaException extends RuntimeException {
    public NedozvoljenaRegistracijaException(String message) {
        super("Korisnik sa tim korisnickim imenom vec postoji ili je firma sa tim Pibom vec u sistemu!");
    }
}
