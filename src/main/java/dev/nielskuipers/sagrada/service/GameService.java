package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.model.game.Game;
import dev.nielskuipers.sagrada.model.game.GameState;
import dev.nielskuipers.sagrada.model.game.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    public Game createGame(Player player) {
       Game game = new Game();

       return game;
    }
}
