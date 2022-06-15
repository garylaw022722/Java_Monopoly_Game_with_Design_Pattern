package Monopoly;

import java.util.*;

public class createPlayerCommand implements Command {

    public void execute(Scanner sc, Game game, GameData gameData) {
        int num = 0;
        System.out.println("How many people will join the game?");
        while (true) {
            num = sc.nextInt();
            if (num <= 1)
                System.out.println("Please input again [ number should be >=1 ]");
            else if (num > 6)
                System.out.println("Please input again [ number should be <=6 ]");
            else
                break;
        }

        for (int s = 0; s < num; s++) {
            Square starter = (Square) game.getSquareList().get(0);// add player in square ,start at 0;
            Player p = new Player("P" + (s + 1), 1500, "Gaming", starter);
            game.addPlayer(p); // creat player
            starter.addPlayerIN_Square(p);

        }
    }

}
