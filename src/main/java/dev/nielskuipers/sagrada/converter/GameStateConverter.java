package dev.nielskuipers.sagrada.converter;

import dev.nielskuipers.sagrada.model.GameState;
import jakarta.persistence.AttributeConverter;

public class GameStateConverter implements AttributeConverter<GameState, String> {

    @Override
    public String convertToDatabaseColumn(GameState gameState) {
        return gameState.toString();
    }

    @Override
    public GameState convertToEntityAttribute(String s) {
        return GameState.valueOf(s);
    }
}
