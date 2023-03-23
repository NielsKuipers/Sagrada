package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.PatternCardModelAssembler;
import dev.nielskuipers.sagrada.exception.CardExceptions.PatternCardNotFoundException;
import dev.nielskuipers.sagrada.model.game.PatternCard;
import dev.nielskuipers.sagrada.repository.PatternCardRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/")
public class PatternCardController {
    private final PatternCardModelAssembler assembler;
    private final PatternCardRepository repository;

    public PatternCardController(PatternCardRepository repository, PatternCardModelAssembler assembler) {
        this.assembler = assembler;
        this.repository = repository;
    }

    //get pattern card
    @GetMapping("/patterncard/{cardId}")
    public EntityModel<PatternCard> one(@PathVariable int cardId) {
        PatternCard card = repository.findById(cardId).orElseThrow(() -> new PatternCardNotFoundException(cardId));

        return assembler.toModel(card);
    }

    //get all pattern cards
    @GetMapping("/patterncard")
    public CollectionModel<EntityModel<PatternCard>> all() {
        List<EntityModel<PatternCard>> patternCards = new ArrayList<>();
        repository.findAll().forEach(card -> patternCards.add(assembler.toModel(card)));

        return CollectionModel.of(patternCards, linkTo(methodOn(PatternCardController.class).all()).withSelfRel());
    }
}
