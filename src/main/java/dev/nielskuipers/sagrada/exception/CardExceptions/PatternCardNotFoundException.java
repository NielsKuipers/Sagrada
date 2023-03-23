package dev.nielskuipers.sagrada.exception.CardExceptions;

public class PatternCardNotFoundException extends RuntimeException {
    public PatternCardNotFoundException(int patternCardId) {
        super("Could not find pattern card with id: " + patternCardId);
    }
}
