package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.assembler.GameObjectiveCardModelAssembler;
import dev.nielskuipers.sagrada.controller.GameObjectiveCardController;
import dev.nielskuipers.sagrada.exception.CardExceptions.ObjectiveCardNotFoundException;
import dev.nielskuipers.sagrada.exception.GameExceptions.GameNotFoundException;
import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GameObjectiveCard;
import dev.nielskuipers.sagrada.repository.GameObjectiveCardRepository;
import dev.nielskuipers.sagrada.repository.GameRepository;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GameObjectiveCardService {
    private final GameObjectiveCardRepository repository;
    private final GameRepository gameRepository;
    private final ObjectiveCardRepository objectiveCardRepository;
    private final GameObjectiveCardModelAssembler assembler;

    public GameObjectiveCardService(GameObjectiveCardRepository repository, GameRepository gameRepository, ObjectiveCardRepository objectiveCardRepository, GameObjectiveCardModelAssembler assembler) {
        this.repository = repository;
        this.gameRepository = gameRepository;
        this.objectiveCardRepository = objectiveCardRepository;
        this.assembler = assembler;
    }

    //get an objective card from a game
    public EntityModel<GameObjectiveCard> one(int gameId, int objectiveCardId) {
        GameObjectiveCard card = repository.findByGameIdAndObjectiveCardId(gameId, objectiveCardId).orElseThrow(() -> new ObjectiveCardNotFoundException(objectiveCardId));

        return assembler.toModel(card);
    }

    //get all cards from a game
    public CollectionModel<EntityModel<GameObjectiveCard>> all(int gameId) {
        List<EntityModel<GameObjectiveCard>> objectiveCards = new ArrayList<>();
        repository.findAllByGameId(gameId).forEach((card -> objectiveCards.add(assembler.toModel(card))));

        return CollectionModel.of(objectiveCards, linkTo(methodOn(GameObjectiveCardController.class).all(gameId)).withSelfRel());
    }

    //add multiple cards to a game
    public CollectionModel<EntityModel<GameObjectiveCard>> addCard(int gameId, List<Integer> objectiveCardIds) {
        List<GameObjectiveCard> gameObjectiveCards = new ArrayList<>();
        List<EntityModel<GameObjectiveCard>> addedCards = new ArrayList<>();

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        objectiveCardRepository.findAllById(objectiveCardIds).forEach(
                gameObjectiveCard -> gameObjectiveCards.add(new GameObjectiveCard(game, gameObjectiveCard))
        );
        repository.saveAll(gameObjectiveCards).forEach(objectiveCard -> addedCards.add(assembler.toModel(objectiveCard)));

        return CollectionModel.of(addedCards, linkTo(methodOn(GameObjectiveCardController.class).all(gameId)).withSelfRel());
    }
}
