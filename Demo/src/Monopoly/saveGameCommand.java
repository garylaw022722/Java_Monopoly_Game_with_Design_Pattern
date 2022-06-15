package Monopoly;

import java.util.*;
import java.sql.*;

public class saveGameCommand implements Command {
    public void execute(Scanner sc, Game game, GameData gameData) {
        String d = "";
        System.out.println("Do you wanna to save the game ? [Y] Save  , [N] Cancel");
        while (true) {

            d = sc.next().toUpperCase();
            if (d.equals("Y") || d.equals("N"))
                break;
            else
                System.out.println("Please enter  [Y] or [N]");

        }
        if (d.equals("Y")) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            game.setSaveTime(timestamp);
            Memento me = new Memento(game);
            gameData.saveGame(me);
            System.out.println("Save Game Successful in " + game.getSaveTime());
            CommandFactory sa = new playerOptionFactory(); // leavingFatory or playerOptionFactory
            try {
                sa.createCommand(game).execute(sc, game, gameData);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (d.equals("N")) {
            try {
                new playerOptionFactory().createCommand(game).execute(sc, game, gameData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}