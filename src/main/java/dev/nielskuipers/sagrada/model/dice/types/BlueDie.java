package dev.nielskuipers.sagrada.model.dice.types;

import dev.nielskuipers.sagrada.model.dice.Die;
import dev.nielskuipers.sagrada.model.dice.dieColor;

public class BlueDie extends Die {
    public BlueDie(int value) {
        super(value);
        super.setColor(dieColor.BLUE);
    }
}
