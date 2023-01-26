package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
