package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.assembler.GameModelAssembler;
import dev.nielskuipers.sagrada.controller.GameController;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GameState;
import dev.nielskuipers.sagrada.repository.GameRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
