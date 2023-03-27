package dev.nielskuipers.sagrada.assembler;

import dev.nielskuipers.sagrada.controller.GameObjectiveCardController;
import dev.nielskuipers.sagrada.model.game.GameObjectiveCard;
import dev.nielskuipers.sagrada.model.game.GameObjectiveCardId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameObjectiveCardModelAssembler implements RepresentationModelAssembler<GameObjectiveCard, EntityModel<GameObjectiveCard>> {

    @Override
    public EntityModel<GameObjectiveCard> toModel(GameObjectiveCard gameObjectiveCard) {
        GameObjectiveCardId ids = gameObjectiveCard.getId();
        int gameId = ids.getGameId();
        int objectiveCardId = ids.getObjectiveCardId();

        return EntityModel.of(gameObjectiveCard,
                linkTo(methodOn(GameObjectiveCardController.class).getGameObjectiveCard(gameId, objectiveCardId)).withSelfRel(),
                linkTo(methodOn(GameObjectiveCardController.class).all(gameId)).withRel("gameObjectiveCards"));
    }

    @Override
    public CollectionModel<EntityModel<GameObjectiveCard>> toCollectionModel(Iterable<? extends GameObjectiveCard> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
