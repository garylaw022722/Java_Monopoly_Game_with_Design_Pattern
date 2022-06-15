package Monopoly;

import java.util.*;

public class showOwnPropertyCommand implements Command {

    public void execute(Scanner sc, Game game, GameData gameData) {
        Player p = game.getPlayerList().peek();
        String whiteSpace = " ".repeat(4);
        System.out.println("Pos" + whiteSpace + "Name" + whiteSpace + "Price" + "  Rent");
        p.getAllProperty().forEach(ele -> {
            int pos = ele.getPos();
            String name = ele.getName();
            int proPrice = ele.getPrice();
            int rent = ele.getRent();
            System.out.println((pos + 1) + whiteSpace + name + whiteSpace + proPrice + "  " + rent);

        });
        try {
            Command cm = new playerOptionFactory().createCommand(game);
            cm.execute(sc, game, gameData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
