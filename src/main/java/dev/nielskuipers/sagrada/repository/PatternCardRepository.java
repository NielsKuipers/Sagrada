package dev.nielskuipers.sagrada.repository;

import dev.nielskuipers.sagrada.model.game.cards.PatternCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternCardRepository extends JpaRepository<PatternCard, Integer> {
}
