package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GamePlayerRepository extends CrudRepository<GamePlayer, GamePlayerId> {
    List<GamePlayer> findAllByGameId(int game_id);
    Optional<GamePlayer> findByGameIdAndPlayerId(int game_id, int player_id);
}
