package dev.nielskuipers.sagrada.model.dice;

public abstract class Die {
    private int value;
    private dieColor color;

    public Die(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public dieColor getColor() {
        return color;
    }

    public void setColor(dieColor color) {
        this.color = color;
    }
}
