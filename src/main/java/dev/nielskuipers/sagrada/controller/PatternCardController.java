package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.model.game.cards.PatternCard;
import dev.nielskuipers.sagrada.service.PatternCardService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/patterncard")
public class PatternCardController {
    private final PatternCardService service;

    public PatternCardController(PatternCardService service) {
        this.service = service;
    }

    //get pattern card
    @GetMapping("/{cardId}")
    public EntityModel<PatternCard> one(@PathVariable int cardId) {
        return service.one(cardId);
    }

    //get all pattern cards
    @GetMapping("/")
    public CollectionModel<EntityModel<PatternCard>> all() {
        return service.all();
    }

    //get 2 random pattern cards per player
    @GetMapping("/rand/{amountOfPlayers}")
    public CollectionModel<EntityModel<PatternCard>> rand(@PathVariable int amountOfPlayers) {
        return service.rand(amountOfPlayers);
    }
}
