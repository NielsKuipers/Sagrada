package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.assembler.GamePlayerModelAssembler;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.exception.PlayerExceptions.PlayerNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GamePlayer;
import dev.nielskuipers.sagrada.model.game.Player;
import dev.nielskuipers.sagrada.repository.GamePlayerRepository;
import dev.nielskuipers.sagrada.repository.GameRepository;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
import dev.nielskuipers.sagrada.repository.PlayerRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GamePlayerService {
    private final GamePlayerRepository repository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final ObjectiveCardRepository objectiveCardRepository;
    private final GamePlayerModelAssembler assembler;

    public GamePlayerService(GamePlayerRepository repository, GameRepository gameRepository, PlayerRepository playerRepository, ObjectiveCardRepository objectiveCardRepository, GamePlayerModelAssembler assembler) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.objectiveCardRepository = objectiveCardRepository;
        this.assembler = assembler;
    }

    //join game
    public ResponseEntity<EntityModel<GamePlayer>> addPlayer(int playerId, int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        GamePlayer gamePlayer = new GamePlayer(game, player);
        GamePlayer newGamePlayer = repository.save(gamePlayer);

        return ResponseEntity.created(linkTo(methodOn(GamePlayerService.class).getGamePlayer(gameId, playerId)).toUri()).body(assembler.toModel(newGamePlayer));
    }

    //get a player from a game
    public EntityModel<GamePlayer> getGamePlayer(int gameId, int playerId) {
        GamePlayer player = repository.findByGameIdAndPlayerId(gameId, playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        return assembler.toModel(player);
    }
}
