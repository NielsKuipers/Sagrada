package dev.nielskuipers.sagrada.converter;

import dev.nielskuipers.sagrada.model.dice.*;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;

public class GameDiceConverter implements AttributeConverter<ArrayList<Die>, String> {
    @Override
    public String convertToDatabaseColumn(ArrayList<Die> dice) {
        StringBuilder diestring = new StringBuilder();

        for (Die die : dice) {
            String dieColor = die.getColor().toString();
            int dieValue = die.getValue();

            diestring.append(dieColor.charAt(0));
            diestring.append(dieValue);
        }

        return diestring.toString();
    }

    @Override
    public ArrayList<Die> convertToEntityAttribute(String s) {
        String[] dice = s.split("(?<=\\G.{2})");
        ArrayList<Die> result = new ArrayList<>();

        for (String die : dice) {
            switch (die.charAt(0)) {
                case 'B' -> result.add(new BlueDie(die.charAt(1) - '0'));
                case 'G' -> result.add(new GreenDie(die.charAt(1) - '0'));
                case 'P' -> result.add(new PurpleDie(die.charAt(1) - '0'));
                case 'R' -> result.add(new RedDie(die.charAt(1) - '0'));
                case 'Y' -> result.add(new YellowDie(die.charAt(1) - '0'));
            }
        }
        return result;
    }
}
