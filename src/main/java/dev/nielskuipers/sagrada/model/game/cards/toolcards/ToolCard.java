package dev.nielskuipers.sagrada.model.game.cards.toolcards;

import jakarta.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn(name = "card_type",
discriminatorType = DiscriminatorType.STRING)
public class ToolCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
}

