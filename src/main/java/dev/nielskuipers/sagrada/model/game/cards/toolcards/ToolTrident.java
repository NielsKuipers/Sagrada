package dev.nielskuipers.sagrada.model.game.cards.toolcards;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("trident")
public class ToolTrident extends ToolCard{
    @Transient
    public void doSomething(){

    }
}
