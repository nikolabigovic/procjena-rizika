package com.nb.procjena_rizika.exception;

public class IzmjenaNijeDozvoljenaException extends RuntimeException {
    public IzmjenaNijeDozvoljenaException(String message) {
        super("Nije dozvoljeno mjenajti procjenu 7 dana nakon njene kreacije!");
    }
}
