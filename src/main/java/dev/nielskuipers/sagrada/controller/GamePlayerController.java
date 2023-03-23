package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.GamePlayerModelAssembler;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.exception.CardExceptions.ObjectiveCardNotFoundException;
import dev.nielskuipers.sagrada.exception.PlayerExceptions.PlayerNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.ObjectiveCard;
import dev.nielskuipers.sagrada.model.game.Player;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import dev.nielskuipers.sagrada.repository.GameRepository;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
import dev.nielskuipers.sagrada.repository.PlayerRepository;
import dev.nielskuipers.sagrada.service.GamePlayerService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final ObjectiveCardRepository objectiveCardRepository;
    private final GamePlayerModelAssembler assembler;
    private final GamePlayerService service;

    GamePlayerController(GamePlayerRepository repository, GameRepository gameRepository, PlayerRepository playerRepository, ObjectiveCardRepository objectiveCardRepository, GamePlayerModelAssembler assembler, GamePlayerService service) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.objectiveCardRepository = objectiveCardRepository;
        this.assembler = assembler;
        this.service = service;
    }

    //join game
    @PostMapping("/")
    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(@RequestParam int playerId, @PathVariable int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        GamePlayer gamePlayer = new GamePlayer(game, player);
        GamePlayer newGamePlayer = repository.save(gamePlayer);

        return ResponseEntity.created(linkTo(methodOn(GamePlayerController.class).getGamePlayer(gameId, playerId)).toUri()).body(assembler.toModel(newGamePlayer));
    }

    // update player
    @PatchMapping("/{playerId}")
    @Modifying
    @Transactional
    public ResponseEntity<EntityModel<GamePlayer>> updatePlayer(@RequestParam(required = false) Integer playerScore, @RequestParam(required = false) String boardPattern, @PathVariable int gameId, @PathVariable int playerId) {
        GamePlayer toUpdate = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        if (playerScore != null) toUpdate.setPlayerScore(playerScore);
        if (StringUtils.isNotBlank(boardPattern)) toUpdate.setBoardPattern(boardPattern);

        GamePlayer updatedGamePlayer = repository.save(toUpdate);

        return ResponseEntity.created(linkTo(methodOn(GamePlayerController.class).getGamePlayer(gameId, playerId)).toUri()).body(assembler.toModel(updatedGamePlayer));
    }

    //assign objective card to player
    @PatchMapping("/{playerId}/objectivecard/{objectiveCardId}")
    @Modifying
    @Transactional
    public ResponseEntity<EntityModel<GamePlayer>> assignObjectiveCard(@PathVariable int gameId, @PathVariable int playerId, @PathVariable int objectiveCardId) {
        ObjectiveCard objectiveCard = objectiveCardRepository.findById(objectiveCardId).orElseThrow(() -> new ObjectiveCardNotFoundException(objectiveCardId));
        GamePlayer toUpdate = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        toUpdate.setObjectiveCard(objectiveCard);
        GamePlayer updatedPlayer = repository.save(toUpdate);
        EntityModel<GamePlayer> entityModel = assembler.toModel(updatedPlayer);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //remove player from game
    @DeleteMapping("/{playerId}")
    @Transactional
    public ResponseEntity<?> removePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        repository.deleteByIdGameIdAndIdPlayerId(gameId, playerId);

        return ResponseEntity.noContent().build();
    }

    //get a player from a game
    @GetMapping("/{playerId}")
    public EntityModel<GamePlayer> getGamePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        return service.getGamePlayer(gameId, playerId);
    }

    //get all players from a game
    @GetMapping("/")
    public CollectionModel<EntityModel<GamePlayer>> all(@PathVariable int gameId) {
        List<EntityModel<GamePlayer>> gamePlayers = new ArrayList<>();
        repository.findAllByGameId(gameId).forEach(gamePlayer -> gamePlayers.add(assembler.toModel(gamePlayer)));

        return CollectionModel.of(gamePlayers, linkTo(methodOn(GamePlayerController.class).all(gameId)).withSelfRel());
    }
}
