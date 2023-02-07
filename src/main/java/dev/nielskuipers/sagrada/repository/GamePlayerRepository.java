package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GamePlayerRepository extends CrudRepository<GamePlayer, GamePlayerId> {
    public List<GamePlayer> findAllByGameId(int game_id);
    public GamePlayer findByGameIdAndPlayerId(int game_id, int player_id);


}
