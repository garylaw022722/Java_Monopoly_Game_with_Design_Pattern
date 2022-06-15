package Monopoly;

import java.util.*;

public class LoadGameFactory implements GameFactory {
    public void execute(Scanner sc, Game game, GameData gameData) throws Exception {
        loadSaveFactory().execute(sc, game, gameData);

    }

    public Command loadSaveFactory() {
        return new loadGameCommand();
    }
}
