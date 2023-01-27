package dev.nielskuipers.sagrada.model.game;

import dev.nielskuipers.sagrada.converter.GameDiceConverter;
import dev.nielskuipers.sagrada.converter.GameStateConverter;
import dev.nielskuipers.sagrada.model.dice.Die;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    private int id;

    @OneToMany(mappedBy = "game")
    private List<GamePlayer> gamePlayers;
    @Convert(converter = GameStateConverter.class)
    private GameState state;
    @Convert(converter = GameDiceConverter.class)
    private ArrayList<Die> die;

    public int getId() {
        return this.id;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public ArrayList<Die> getDie() {
        return die;
    }

    public void setDie(ArrayList<Die> die) {
        this.die = die;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
