package dev.nielskuipers.sagrada.model.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class GamePlayer {
    @EmbeddedId
    private final GamePlayerId id = new GamePlayerId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    private Player player;

    private int playerScore;
    private String boardPattern;

    @ManyToOne
    @JoinColumn(name = "objective_card_id")
    private ObjectiveCard objectiveCard;

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

    public ObjectiveCard getObjectiveCard() {
        return objectiveCard;
    }

    public void setObjectiveCard(ObjectiveCard objectiveCard) {
        this.objectiveCard = objectiveCard;
    }

    public GamePlayer() {
    }

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
}
