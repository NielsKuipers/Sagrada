package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.GameModelAssembler;
import dev.nielskuipers.sagrada.exception.GameNotFoundException;
import dev.nielskuipers.sagrada.model.Game;
import dev.nielskuipers.sagrada.repository.GameRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameRepository repository;
    private final GameModelAssembler assembler;

    GameController(GameRepository repository, GameModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public EntityModel<Game> getGame(@PathVariable int id) {
        Game game = repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return assembler.toModel(game);
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<Game>> all() {
        List<EntityModel<Game>> games = new ArrayList<>();
        repository.findAll().forEach(game -> games.add(assembler.toModel(game)));

        return CollectionModel.of(games, linkTo(methodOn(GameController.class).all()).withSelfRel());
    }
}
