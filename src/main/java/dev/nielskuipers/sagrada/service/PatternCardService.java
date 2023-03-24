package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.assembler.PatternCardModelAssembler;
import dev.nielskuipers.sagrada.controller.PatternCardController;
import dev.nielskuipers.sagrada.exception.CardExceptions.PatternCardNotFoundException;
import dev.nielskuipers.sagrada.model.game.PatternCard;
import dev.nielskuipers.sagrada.repository.PatternCardRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PatternCardService {
    private final PatternCardModelAssembler assembler;
    private final PatternCardRepository repository;

    public PatternCardService(PatternCardModelAssembler assembler, PatternCardRepository repository) {
        this.assembler = assembler;
        this.repository = repository;
    }

    //get pattern card
    public EntityModel<PatternCard> one(int cardId) {
        PatternCard card = repository.findById(cardId).orElseThrow(() -> new PatternCardNotFoundException(cardId));

        return assembler.toModel(card);
    }

    //get all pattern cards
    public CollectionModel<EntityModel<PatternCard>> all() {
        List<EntityModel<PatternCard>> patternCards = new ArrayList<>();
        repository.findAll().forEach(card -> patternCards.add(assembler.toModel(card)));

        return CollectionModel.of(patternCards, linkTo(methodOn(PatternCardController.class).all()).withSelfRel());
    }

    //get random pattern card
    public CollectionModel<EntityModel<PatternCard>> rand(int amountOfPlayers) {
        Random rand = new Random();

        List<EntityModel<PatternCard>> allCards = new ArrayList<>();
        List<EntityModel<PatternCard>> patternCards = new ArrayList<>();
        repository.findAll().forEach(card -> allCards.add(assembler.toModel(card)));

        for (int i = 0; i < (amountOfPlayers * 2); i++) {
            int randNum = rand.nextInt((allCards.size()));
            EntityModel<PatternCard> toAdd = allCards.get(randNum);
            patternCards.add(toAdd);

            allCards.remove(randNum);
        }

        return CollectionModel.of(patternCards, linkTo(methodOn(PatternCardController.class).all()).withSelfRel());
    }
}
