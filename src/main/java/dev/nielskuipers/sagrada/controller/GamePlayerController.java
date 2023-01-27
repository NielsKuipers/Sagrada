package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.Player;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/game/{id}/player")
public class GamePlayerController {
    private final GamePlayerRepository repository;

    GamePlayerController(GamePlayerRepository repository) {
        this.repository = repository;
    }

//    @PostMapping("/join")
//    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(@RequestBody Game game, Player player) {
//        GamePlayer gamePlayer = new GamePlayer(game, player);
//        GamePlayer newGamePlayer = repository.save(gamePlayer);
//
//
//    }
}
