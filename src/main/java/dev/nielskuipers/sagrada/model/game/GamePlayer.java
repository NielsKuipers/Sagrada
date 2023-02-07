package dev.nielskuipers.sagrada.model.game;

import jakarta.persistence.*;

@Entity
public class GamePlayer {
    @EmbeddedId
    private GamePlayerId id = new GamePlayerId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    private Player player;

    private int playerScore;
    private String boardPattern;

    public GamePlayerId getId() {
        return id;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getBoardPattern() {
        return boardPattern;
    }

    public void setBoardPattern(String boardPattern) {
        this.boardPattern = boardPattern;
    }

    public GamePlayer(){}
    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
}
