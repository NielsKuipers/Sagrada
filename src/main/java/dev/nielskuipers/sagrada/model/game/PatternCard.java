package dev.nielskuipers.sagrada.model.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PatternCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pattern;
    private int difficulty;

    public PatternCard() {
    }

    public PatternCard(String pattern, int difficulty) {
        this.pattern = pattern;
        this.difficulty = difficulty;
    }

    public String getPattern() {
        return pattern;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getId() {
        return id;
    }
}
