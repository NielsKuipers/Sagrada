package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.GamePlayerModelAssembler;
import dev.nielskuipers.sagrada.exception.PlayerNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import dev.nielskuipers.sagrada.model.game.Player;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/game/{gameId}/player")
public class GamePlayerController {
    private final GamePlayerRepository repository;
    private final GamePlayerModelAssembler assembler;

    GamePlayerController(GamePlayerRepository repository, GamePlayerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

//    @PostMapping("/")
//    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(@RequestBody int playerId, @PathVariable int gameId) {
//        GamePlayer gamePlayer = new GamePlayer(gameId, playerId);
//        GamePlayer newGamePlayer = repository.save(gamePlayer);
//
//        return ResponseEntity
//                .created(linkTo(methodOn(GamePlayerController.class).getGamePlayer(gameId, playerId)).toUri())
//                .body(assembler.toModel(newGamePlayer));
//    }

    @GetMapping("/{playerId}")
    public EntityModel<GamePlayer> getGamePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        GamePlayer player = repository.findByGameIdAndPlayerId(gameId, playerId);

        return assembler.toModel(player);
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<GamePlayer>> all(@PathVariable int gameId) {
        List<EntityModel<GamePlayer>> gamePlayers = new ArrayList<>();
        repository.findAllByGameId(gameId).forEach(gamePlayer -> gamePlayers.add(assembler.toModel(gamePlayer)));

        return CollectionModel.of(gamePlayers, linkTo(methodOn(GamePlayerController.class).all(gameId)).withSelfRel());
    }
}
