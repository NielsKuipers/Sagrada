package dev.nielskuipers.sagrada.controller;

import dev.nielskuipers.sagrada.model.game.ObjectiveCard;
import dev.nielskuipers.sagrada.service.ObjectiveCardService;
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
   private final ObjectiveCardService service;

   public ObjectiveCardController(ObjectiveCardService service) {
      this.service = service;
   }

   //get objective card
   @GetMapping("/objectivecard/{cardId}")
   public EntityModel<ObjectiveCard> one(@PathVariable int cardId) {
      return service.one(cardId);
   }

   //get all objective cards
   @GetMapping("/objectivecard")
   public CollectionModel<EntityModel<ObjectiveCard>> all() {
      return service.all();
   }

   //get random objective cards based on amount of players
   @GetMapping("/objectivecard/rand/{amountOfPlayers}")
   public CollectionModel<EntityModel<ObjectiveCard>> rand(@PathVariable int amountOfPlayers) {
      return service.rand(amountOfPlayers);
   }
}
