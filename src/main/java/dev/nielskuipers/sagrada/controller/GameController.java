package dev.nielskuipers.sagrada.controller;

import com.github.fge.jsonpatch.JsonPatch;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.service.GameService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

    //update a game
    @PatchMapping("/{gameId}")
    @Modifying
    @Transactional
    public ResponseEntity<EntityModel<Game>> updateGame(@PathVariable int gameId, @RequestBody JsonPatch patch) {
        return service.updateGame(gameId, patch);
    }
}
