package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GameState;
import dev.nielskuipers.sagrada.service.GameService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService service;

    GameController(GameService service) {
        this.service = service;
    }

    //get a game
    @GetMapping("/{id}")
    public EntityModel<Game> one(@PathVariable int id) {
        return service.one(id);
    }

    //get all games
    @GetMapping("/")
    public CollectionModel<EntityModel<Game>> all() {
        return service.all();
    }

    //create a new game
    @PostMapping("/")
    ResponseEntity<EntityModel<Game>> newGame() {
        return service.create();
    }
}
