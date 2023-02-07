package dev.nielskuipers.sagrada.model.dice.types;

import dev.nielskuipers.sagrada.model.dice.Die;
import dev.nielskuipers.sagrada.model.dice.dieColor;

public class GreenDie extends Die {
    public GreenDie(int value) {
        super(value);
        super.setColor(dieColor.GREEN);
    }
}
