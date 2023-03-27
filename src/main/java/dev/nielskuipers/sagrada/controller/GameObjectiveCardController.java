package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.model.game.GameObjectiveCard;
import dev.nielskuipers.sagrada.service.GameObjectiveCardService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game/{gameId}/objectivecard")
public class GameObjectiveCardController {

    private final GameObjectiveCardService service;

    public GameObjectiveCardController(GameObjectiveCardService service) {
        this.service = service;
    }

    //get a card from a game
    @GetMapping("/{objectiveCardId}")
    public EntityModel<GameObjectiveCard> getGameObjectiveCard(@PathVariable int gameId, @PathVariable int objectiveCardId) {
        return service.one(gameId, objectiveCardId);
    }

    //get all objective cards from a game
    @GetMapping("/")
    public CollectionModel<EntityModel<GameObjectiveCard>> all(@PathVariable int gameId) {
        return service.all(gameId);
    }

    //add multiple cards to a game
    @PostMapping("/{objectiveCardIds}")
    public CollectionModel<EntityModel<GameObjectiveCard>> addCard(@PathVariable int gameId, @PathVariable List<Integer> objectiveCardIds) {
        return service.addCard(gameId, objectiveCardIds);
    }
}
