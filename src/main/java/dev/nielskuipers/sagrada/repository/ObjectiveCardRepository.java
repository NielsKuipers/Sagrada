package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.cards.ObjectiveCard;
import org.springframework.data.repository.CrudRepository;

public interface ObjectiveCardRepository extends CrudRepository<ObjectiveCard, Integer> {
}
