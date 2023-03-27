package dev.nielskuipers.sagrada.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import dev.nielskuipers.sagrada.assembler.GameModelAssembler;
import dev.nielskuipers.sagrada.controller.GameController;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.repository.GameRepository;
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
public class GameService {
    private final GameRepository repository;
    private final GameModelAssembler assembler;

    public GameService(GameRepository repository, GameModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //get a game
    public EntityModel<Game> one(int id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return assembler.toModel(game);
    }

    //get all games
    public CollectionModel<EntityModel<Game>> all() {
        List<EntityModel<Game>> games = new ArrayList<>();
        repository.findAll().forEach(game -> games.add(assembler.toModel(game)));

        return CollectionModel.of(games, linkTo(methodOn(GameController.class).all()).withSelfRel());
    }

    //create a new game
    public ResponseEntity<EntityModel<Game>> create() {
        Game newGame = repository.save(new Game());

        EntityModel<Game> entityModel = assembler.toModel(newGame);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //update a game
    public ResponseEntity<EntityModel<Game>> updateGame(int gameId, JsonPatch patch) {
        Game game = repository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Game updatedGame;

        //convert game to json and apply patch, then map it to a game object
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(game, JsonNode.class));
            updatedGame = objectMapper.treeToValue(patched, Game.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        EntityModel<Game> entityModel = assembler.toModel(updatedGame);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
