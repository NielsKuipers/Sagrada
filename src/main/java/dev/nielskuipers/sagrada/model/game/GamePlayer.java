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
    private int tokens;

    @ManyToOne
    @JoinColumn(name = "pattern_card_id")
    private PatternCard patternCard;

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

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public PatternCard getPatternCard() {
        return patternCard;
    }

    public void setPatternCard(PatternCard patternCard) {
        this.patternCard = patternCard;
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
