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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> players;
    @Convert(converter = GameStateConverter.class)
    private GameState state;
    @Convert(converter = GameDiceConverter.class)
    private List<Die> die;

    public int getId() {
        return this.id;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<Die> getDie() {
        return die;
    }

    public void setDie(List<Die> die) { this.die = die; }

    public List<GamePlayer> getGamePlayers() {
        return players;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.players = gamePlayers;
    }
}
