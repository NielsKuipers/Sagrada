package dev.nielskuipers.sagrada.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import dev.nielskuipers.sagrada.assembler.GamePlayerModelAssembler;
import dev.nielskuipers.sagrada.controller.GamePlayerController;
import dev.nielskuipers.sagrada.exception.CardExceptions.ObjectiveCardNotFoundException;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.exception.PlayerExceptions.PlayerNotFoundException;
import dev.nielskuipers.sagrada.model.game.*;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import dev.nielskuipers.sagrada.repository.GameRepository;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
import dev.nielskuipers.sagrada.repository.PlayerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GamePlayerService {
    private final GamePlayerRepository repository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final ObjectiveCardRepository objectiveCardRepository;
    private final GamePlayerModelAssembler assembler;

    public GamePlayerService(GamePlayerRepository repository, GameRepository gameRepository, PlayerRepository playerRepository, ObjectiveCardRepository objectiveCardRepository, GamePlayerModelAssembler assembler) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.objectiveCardRepository = objectiveCardRepository;
        this.assembler = assembler;
    }

    //get a player from a game
    public EntityModel<GamePlayer> one(int gameId, int playerId) {
        GamePlayer player = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        return assembler.toModel(player);
    }

    //get all players from a game
    public CollectionModel<EntityModel<GamePlayer>> all(int gameId) {
        List<EntityModel<GamePlayer>> gamePlayers = new ArrayList<>();
        repository.findAllByGameId(gameId).forEach(gamePlayer -> gamePlayers.add(assembler.toModel(gamePlayer)));

        return CollectionModel.of(gamePlayers, linkTo(methodOn(GamePlayerController.class).all(gameId)).withSelfRel());
    }

    //remove a player from a game
    public ResponseEntity<?> removePlayer(int gameId, int playerId) {
        repository.deleteById(new GamePlayerId(gameId, playerId));

        return ResponseEntity.noContent().build();
    }

    //join game
    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(int playerId, int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        GamePlayer gamePlayer = new GamePlayer(game, player);
        GamePlayer newGamePlayer = repository.save(gamePlayer);

        EntityModel<GamePlayer> entityModel = assembler.toModel(newGamePlayer);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //update entities in player
    public ResponseEntity<EntityModel<GamePlayer>> updatePlayer(int playerId, int gameId, JsonPatch patch) {
        GamePlayer player = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
        GamePlayer updatedPlayer;

        //convert gameplayer to json and apply patch, then map it to a gameplayer object
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(player, JsonNode.class));
            updatedPlayer = objectMapper.treeToValue(patched, GamePlayer.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        EntityModel<GamePlayer> entityModel = assembler.toModel(updatedPlayer);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //assign objective card to player
    public ResponseEntity<EntityModel<GamePlayer>> assignObjectiveCard(int gameId, int playerId, int objectiveCardId) {
        ObjectiveCard objectiveCard = objectiveCardRepository.findById(objectiveCardId).orElseThrow(() -> new ObjectiveCardNotFoundException(objectiveCardId));
        GamePlayer toUpdate = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        toUpdate.setObjectiveCard(objectiveCard);
        GamePlayer updatedPlayer = repository.save(toUpdate);

        EntityModel<GamePlayer> entityModel = assembler.toModel(updatedPlayer);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
