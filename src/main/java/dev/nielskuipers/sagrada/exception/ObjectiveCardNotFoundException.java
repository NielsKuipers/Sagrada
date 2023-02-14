package dev.nielskuipers.sagrada.exception;

public class ObjectiveCardNotFoundException extends  RuntimeException{
    public ObjectiveCardNotFoundException(int objectiveCardId) {
        super("Could not find objective card with id: " + objectiveCardId);
    }
}
