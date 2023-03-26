package dev.nielskuipers.sagrada.assembler;

import dev.nielskuipers.sagrada.controller.PatternCardController;
import dev.nielskuipers.sagrada.model.game.cards.PatternCard;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatternCardModelAssembler implements RepresentationModelAssembler<PatternCard, EntityModel<PatternCard>> {

    @Override
    public EntityModel<PatternCard> toModel(PatternCard entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PatternCardController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(PatternCardController.class).all()).withRel("patternCards"));
    }

    @Override
    public CollectionModel<EntityModel<PatternCard>> toCollectionModel(Iterable<? extends PatternCard> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
