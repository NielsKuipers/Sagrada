package dev.nielskuipers.sagrada.model.game;

import jakarta.persistence.*;

@Entity
@Table(name = "game_player")
@IdClass(GamePlayerId.class)
public class GamePlayer {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    public GamePlayer() {
        playerScore = 0;
        boardPattern = "";
    }

    public GamePlayer(Game game, Player player) {
        playerScore = 0;
        boardPattern = "";
        this.game = game;
        this.player = player;
    }

    private int playerScore;
    private String boardPattern;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
