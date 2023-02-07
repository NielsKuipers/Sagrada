package dev.nielskuipers.sagrada.model.dice.types;

import dev.nielskuipers.sagrada.model.dice.Die;
import dev.nielskuipers.sagrada.model.dice.dieColor;

public class RedDie extends Die {
    public RedDie(int value) {
        super(value);
        super.setColor(dieColor.RED);
    }
}
