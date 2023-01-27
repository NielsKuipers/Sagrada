package dev.nielskuipers.sagrada.assembler;

import dev.nielskuipers.sagrada.controller.GameController;
import dev.nielskuipers.sagrada.model.game.Game;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {
    @Override
    public EntityModel<Game> toModel(Game game) {
        return EntityModel.of(game,
                linkTo(methodOn(GameController.class).getGame(game.getId())).withSelfRel(),
                linkTo(methodOn(GameController.class).all()).withRel("games"));
    }

    @Override
    public CollectionModel<EntityModel<Game>> toCollectionModel(Iterable<? extends Game> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
