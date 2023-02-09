package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.GamePlayerModelAssembler;
import dev.nielskuipers.sagrada.exception.GameNotFoundException;
import dev.nielskuipers.sagrada.exception.PlayerNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.GamePlayerId;
import dev.nielskuipers.sagrada.model.game.Player;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import dev.nielskuipers.sagrada.repository.GameRepository;
import dev.nielskuipers.sagrada.repository.PlayerRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final GamePlayerModelAssembler assembler;

    GamePlayerController(GamePlayerRepository repository, GameRepository gameRepository, PlayerRepository playerRepository, GamePlayerModelAssembler assembler) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.assembler = assembler;
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(@RequestParam int playerId, @PathVariable int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        GamePlayer gamePlayer = new GamePlayer(game, player);
        GamePlayer newGamePlayer = repository.save(gamePlayer);

        return ResponseEntity.created(linkTo(methodOn(GamePlayerController.class).getGamePlayer(gameId, playerId)).toUri()).body(assembler.toModel(newGamePlayer));
    }

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

    @DeleteMapping("/{playerId}")
    @Transactional
    public ResponseEntity<?> removePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        repository.deleteByGameIdAndPlayerId(gameId, playerId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{playerId}")
    public EntityModel<GamePlayer> getGamePlayer(@PathVariable int gameId, @PathVariable int playerId) {
        GamePlayer player = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        return assembler.toModel(player);
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<GamePlayer>> all(@PathVariable int gameId) {
        List<EntityModel<GamePlayer>> gamePlayers = new ArrayList<>();
        repository.findAllByGameId(gameId).forEach(gamePlayer -> gamePlayers.add(assembler.toModel(gamePlayer)));

        return CollectionModel.of(gamePlayers, linkTo(methodOn(GamePlayerController.class).all(gameId)).withSelfRel());
    }
}
