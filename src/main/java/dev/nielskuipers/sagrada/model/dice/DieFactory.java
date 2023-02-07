package dev.nielskuipers.sagrada.model.dice;

import dev.nielskuipers.sagrada.model.dice.types.*;

public class DieFactory {
    public Die createDie(dieColor color, int value) {
        Die die = null;

        switch (color) {
            case BLUE -> die = new BlueDie(value);
            case GREEN -> die = new GreenDie(value);
            case PURPLE -> die = new PurpleDie(value);
            case RED -> die = new RedDie(value);
            case YELLOW -> die = new YellowDie(value);
        }

        return die;
    }
}
