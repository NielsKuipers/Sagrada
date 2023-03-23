package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.assembler.ObjectiveCardModelAssembler;
import dev.nielskuipers.sagrada.exception.CardExceptions.ObjectiveCardNotFoundException;
import dev.nielskuipers.sagrada.model.game.ObjectiveCard;
import dev.nielskuipers.sagrada.repository.ObjectiveCardRepository;
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
public class ObjectiveCardController {
   private final ObjectiveCardModelAssembler assembler;
   private final ObjectiveCardRepository repository;

   public ObjectiveCardController(ObjectiveCardRepository repository, ObjectiveCardModelAssembler assembler) {
      this.assembler = assembler;
      this.repository = repository;
   }

   //get objective card
   @GetMapping("/objectivecard/{cardId}")
   public EntityModel<ObjectiveCard> one(@PathVariable int cardId) {
      ObjectiveCard card = repository.findById(cardId).orElseThrow(() -> new ObjectiveCardNotFoundException(cardId));

      return assembler.toModel(card);
   }

   //get all objective cards
   @GetMapping("/objectivecard")
   public CollectionModel<EntityModel<ObjectiveCard>> all() {
      List<EntityModel<ObjectiveCard>> objectiveCards = new ArrayList<>();
      repository.findAll().forEach(card -> objectiveCards.add(assembler.toModel(card)));

      return CollectionModel.of(objectiveCards, linkTo(methodOn(ObjectiveCardController.class).all()).withSelfRel());
   }
}
