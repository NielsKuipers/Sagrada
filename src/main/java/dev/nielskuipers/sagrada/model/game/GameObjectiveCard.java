package dev.nielskuipers.sagrada.model.game;

import dev.nielskuipers.sagrada.model.game.cards.ObjectiveCard;
import jakarta.persistence.*;

@Entity
public class GameObjectiveCard {
    @EmbeddedId
    private final GameObjectiveCardId id = new GameObjectiveCardId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private Game game;

    @ManyToOne
    @MapsId("objectiveCardId")
    private ObjectiveCard objectiveCard;

    public GameObjectiveCard() {}

    public GameObjectiveCard(Game game, ObjectiveCard objectiveCard) {
        this.game = game;
        this.objectiveCard = objectiveCard;
    }

    public GameObjectiveCardId getId() {
        return id;
    }

    public ObjectiveCard getObjectiveCard() {
        return objectiveCard;
    }

    public void setObjectiveCard(ObjectiveCard objectiveCard) {
        this.objectiveCard = objectiveCard;
    }
}
