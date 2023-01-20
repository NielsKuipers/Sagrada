package dev.nielskuipers.sagrada.model;

import dev.nielskuipers.sagrada.model.dice.Die;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Game {
    @Id
    private int gameId;
    private ArrayList<Die> dice;
    private ArrayList<Player> players;
    private GameState state;

    public int getGameId() {
        return this.gameId;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public void setDice(ArrayList<Die> dice) {
        this.dice = dice;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
