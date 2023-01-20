package dev.nielskuipers.sagrada.model.dice;

public class BlueDie extends Die {
    public BlueDie(int value) {
        super(value);
        super.setColor(dieColor.BLUE);
    }
}
