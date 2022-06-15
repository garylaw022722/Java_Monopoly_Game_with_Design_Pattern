package Monopoly;

import java.util.*;

public class newGameFactory implements GameFactory {

    public void execute(Scanner sc, Game game, GameData gameData) throws Exception {
        createPlayerFacotory().execute(sc, game, gameData);
        startGame().execute(sc, game, gameData);

    }

    public Command createPlayerFacotory() { // selectPlayer
        return new createPlayerCommand();
    }

    public Command startGame() { // purchase
        return new startGameCommand();
    }

}