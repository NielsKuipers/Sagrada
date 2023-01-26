package dev.nielskuipers.sagrada.model;

import dev.nielskuipers.sagrada.Converter.GameDiceConverter;
import dev.nielskuipers.sagrada.Converter.GameStateConverter;
import dev.nielskuipers.sagrada.model.dice.Die;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Game {
    @Id
    private int id;
    @ManyToMany
    @JoinTable(name = "game_players", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"))
    private Collection<Player> players;
    @Convert(converter = GameStateConverter.class)
    private GameState state;
    @Convert(converter = GameDiceConverter.class)
    private ArrayList<Die> die;

    public long getId() {
        return this.id;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
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
}
