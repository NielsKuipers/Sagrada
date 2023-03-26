package dev.nielskuipers.sagrada.assembler;

import dev.nielskuipers.sagrada.controller.ObjectiveCardController;
import dev.nielskuipers.sagrada.model.game.cards.ObjectiveCard;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ObjectiveCardModelAssembler implements RepresentationModelAssembler<ObjectiveCard, EntityModel<ObjectiveCard>> {
    @Override
    public EntityModel<ObjectiveCard> toModel(ObjectiveCard entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ObjectiveCardController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ObjectiveCardController.class).all()).withRel("objectiveCards"));
    }

    @Override
    public CollectionModel<EntityModel<ObjectiveCard>> toCollectionModel(Iterable<? extends ObjectiveCard> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
