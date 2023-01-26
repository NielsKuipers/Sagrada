package dev.nielskuipers.sagrada.service;

import dev.nielskuipers.sagrada.model.Game;
import dev.nielskuipers.sagrada.model.GameState;
import dev.nielskuipers.sagrada.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    public Game createGame(Player player) {
       Game game = new Game();
       game.addPlayer(player);
       game.setState(GameState.OPEN);

       return game;
    }
}
