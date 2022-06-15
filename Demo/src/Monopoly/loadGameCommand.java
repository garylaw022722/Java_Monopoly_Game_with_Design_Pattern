package Monopoly;

import java.util.*;

public class loadGameCommand implements Command {
    public void execute(Scanner sc, Game game, GameData gameData) {
        Enumeration data = gameData.getAll_Data().elements();
        playerOptionFactory userOper = new playerOptionFactory();
        try {
            if (!data.hasMoreElements()) {
                System.out.println("No Save found");
                // to locate the case on game processing or
                // selection of game start and loading game
                if (game.getPlayerList().size() != 0) {
                    userOper.createCommand(game).execute(sc, game, gameData);
                }

                return;
            }

            System.out.println();
            String decision = "";
            System.out.println("Do you want to load Game Data ? [Y] Yes [N] No");

            while (true) {
                decision = sc.next().toUpperCase();
                if (decision.equals("Y") || decision.equals("N"))
                    break;
                else
                    System.out.println("Please enter  [Y] Yes or [N] No");
            }

            if (decision.equals("Y")) {
                int countSave = 0;

                while (data.hasMoreElements()) {
                    Memento savedGame = (Memento) data.nextElement();
                    System.out.println("[" + countSave + "]" + savedGame.getSaveTime());
                    countSave++;
                }
                int numData = sc.nextInt();
                gameData.loadSave(numData).restore();
                restartGame(sc, game, gameData);

            } else if (decision.equals("N")) {
                userOper.createCommand(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void restartGame(Scanner sc, Game game, GameData gameData) {
        CommandFactory cf = new playerOptionFactory();
        try {
            GAME_INTERFACE gi = new GAME_INTERFACE(game);
            gi.displayGameBoard(game.getRound());
            cf.createCommand(game).execute(sc, game, gameData);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
