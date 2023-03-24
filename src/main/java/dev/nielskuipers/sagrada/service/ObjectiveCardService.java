package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.assembler.ObjectiveCardModelAssembler;
import dev.nielskuipers.sagrada.controller.ObjectiveCardController;
import dev.nielskuipers.sagrada.exception.CardExceptions.ObjectiveCardNotFoundException;
import dev.nielskuipers.sagrada.model.game.ObjectiveCard;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ObjectiveCardService {
    private final ObjectiveCardModelAssembler assembler;
    private final ObjectiveCardRepository repository;

    public ObjectiveCardService(ObjectiveCardModelAssembler assembler, ObjectiveCardRepository repository) {
        this.assembler = assembler;
        this.repository = repository;
    }

    //get objective card
    public EntityModel<ObjectiveCard> one(int cardId) {
        ObjectiveCard card = repository.findById(cardId).orElseThrow(() -> new ObjectiveCardNotFoundException(cardId));

        return assembler.toModel(card);
    }

    //get all objective cards
    public CollectionModel<EntityModel<ObjectiveCard>> all() {
        List<EntityModel<ObjectiveCard>> objectiveCards = new ArrayList<>();
        repository.findAll().forEach(card -> objectiveCards.add(assembler.toModel(card)));

        return CollectionModel.of(objectiveCards, linkTo(methodOn(ObjectiveCardController.class).all()).withSelfRel());
    }

    //get random objective cards based on amount of players
    public CollectionModel<EntityModel<ObjectiveCard>> rand(int amountOfPlayers) {
        Random rand = new Random();

        List<EntityModel<ObjectiveCard>> allCards = new ArrayList<>();
        List<EntityModel<ObjectiveCard>> objectiveCards = new ArrayList<>();
        repository.findAll().forEach(card -> allCards.add(assembler.toModel(card)));

        for (int i = 0; i < amountOfPlayers; i++) {
            int randNum = rand.nextInt((allCards.size()));
            EntityModel<ObjectiveCard> toAdd = allCards.get(randNum);
            objectiveCards.add(toAdd);

            allCards.remove(randNum);
        }

        return CollectionModel.of(objectiveCards, linkTo(methodOn(ObjectiveCardController.class).all()).withSelfRel());
    }
}
