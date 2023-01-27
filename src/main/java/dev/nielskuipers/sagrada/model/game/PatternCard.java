package dev.nielskuipers.sagrada.model.game;

import org.springframework.data.annotation.Id;

public class PatternCard {
    @Id
    Long id;
    String pattern;
    int difficulty;

    public String getPattern() {
        return pattern;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
