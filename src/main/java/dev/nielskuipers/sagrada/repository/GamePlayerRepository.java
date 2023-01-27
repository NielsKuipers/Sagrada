package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import org.springframework.data.repository.CrudRepository;

public interface GamePlayerRepository extends CrudRepository<GamePlayer, GamePlayerId> {
}
