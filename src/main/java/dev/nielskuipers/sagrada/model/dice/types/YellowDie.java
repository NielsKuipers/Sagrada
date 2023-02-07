package dev.nielskuipers.sagrada.model.dice.types;

import dev.nielskuipers.sagrada.model.dice.Die;
import dev.nielskuipers.sagrada.model.dice.dieColor;

public class YellowDie extends Die {
    public YellowDie(int value) {
        super(value);
        super.setColor(dieColor.YELLOW);
    }
}
