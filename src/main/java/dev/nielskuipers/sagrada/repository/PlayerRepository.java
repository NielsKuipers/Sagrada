package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
