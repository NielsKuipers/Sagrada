package dev.nielskuipers.sagrada.controller;

import com.github.fge.jsonpatch.JsonPatch;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.service.GamePlayerService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game/{gameId}/player")
public class GamePlayerController {
    private final GamePlayerService service;

    GamePlayerController(GamePlayerService service) {
        this.service = service;
    }

    //join game
    @PostMapping("/{playerId}")
    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(@PathVariable int playerId, @PathVariable int gameId) {
        return service.addPlayer(playerId, gameId);
    }

    // update player
    @PatchMapping("/{playerId}")
    @Modifying
    @Transactional
    public ResponseEntity<EntityModel<GamePlayer>> updatePlayer(@PathVariable int playerId, @PathVariable int gameId, @RequestBody JsonPatch patch ) {
        return service.updatePlayer(playerId, gameId, patch);
    }

    //assign objective card to player
    @PatchMapping("/{playerId}/objectivecard/{objectiveCardId}")
    @Modifying
    @Transactional
    public ResponseEntity<EntityModel<GamePlayer>> assignObjectiveCard(@PathVariable int gameId, @PathVariable int playerId, @PathVariable int objectiveCardId) {
        return service.assignObjectiveCard(gameId, playerId, objectiveCardId);
    }

    //remove player from game
    @DeleteMapping("/{playerId}")
    @Transactional
    public ResponseEntity<?> removePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        return service.removePlayer(gameId, playerId);
    }

    //get a player from a game
    @GetMapping("/{playerId}")
    public EntityModel<GamePlayer> getGamePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        return service.getGamePlayer(gameId, playerId);
    }

    //get all players from a game
    @GetMapping("/")
    public CollectionModel<EntityModel<GamePlayer>> all(@PathVariable int gameId) {
        return service.all(gameId);
    }
}
