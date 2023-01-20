package dev.nielskuipers.sagrada.model;

import org.springframework.data.annotation.Id;

public class PatternCard {
    @Id
    int id;
    String pattern;
    int difficulty;

    public String getPattern() {
        return pattern;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
