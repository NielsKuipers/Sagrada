package dev.nielskuipers.sagrada.assembler;

import dev.nielskuipers.sagrada.controller.GamePlayerController;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GamePlayerModelAssembler implements RepresentationModelAssembler<GamePlayer, EntityModel<GamePlayer>> {
    @Override
    public EntityModel<GamePlayer> toModel(GamePlayer gamePlayer) {
        GamePlayerId ids = gamePlayer.getId();
        int gameId = ids.getGameId();
        int playerId = ids.getPlayerId();

        return EntityModel.of(gamePlayer,
                linkTo(methodOn(GamePlayerController.class).getGamePlayer(gameId, playerId)).withSelfRel(),
                linkTo(methodOn(GamePlayerController.class).all(gameId)).withRel("gamePlayers"));
    }

    @Override
    public CollectionModel<EntityModel<GamePlayer>> toCollectionModel(Iterable<? extends GamePlayer> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
