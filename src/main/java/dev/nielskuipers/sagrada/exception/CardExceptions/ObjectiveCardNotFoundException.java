package dev.nielskuipers.sagrada.exception.CardExceptions;

public class ObjectiveCardNotFoundException extends  RuntimeException{
    public ObjectiveCardNotFoundException(int objectiveCardId) {
        super("Could not find objective card with id: " + objectiveCardId);
    }
}
