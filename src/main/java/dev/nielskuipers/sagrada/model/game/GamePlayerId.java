package dev.nielskuipers.sagrada.model.game;

import java.io.Serializable;
import java.util.Objects;

public class GamePlayerId implements Serializable {
    private int game;
    private int player;

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayerId that = (GamePlayerId) o;
        return game == that.game && player == that.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, player);
    }
}
