package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.Exception.GameNotFoundException;
import dev.nielskuipers.sagrada.model.Game;
import dev.nielskuipers.sagrada.repository.GameRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameRepository repository;

    GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public EntityModel<Game> getGame(@PathVariable int id) {
        Game game = repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return EntityModel.of(game,
                linkTo(methodOn(GameController.class).getGame(id)).withSelfRel(),
                linkTo(methodOn(GameController.class).all()).withRel("games"));
    }

    @GetMapping("/")
    Iterable<Game> all() {
        return repository.findAll();
    }
}
