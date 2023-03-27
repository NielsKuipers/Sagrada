package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.GameObjectiveCard;
import dev.nielskuipers.sagrada.model.game.GameObjectiveCardId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameObjectiveCardRepository extends CrudRepository<GameObjectiveCard, GameObjectiveCardId> {
    List<GameObjectiveCard> findAllByGameId(int game_id);
    Optional<GameObjectiveCard> findByGameIdAndObjectiveCardId(int game_id, int player_id);
}
