package Monopoly;

import java.util.*;

public class playerOptionFactory implements CommandFactory {
    String[] playerActionList = { "Monopoly.rollingDic_Command", "Monopoly.showOwnPropertyCommand", "Monopoly.loadGameCommand",
            "Monopoly.saveGameCommand" };

    public Command createCommand(Game game) throws Exception {
        Scanner sc = new Scanner(System.in);
        Player p = game.getPlayerList().peek();
        int option = 0;
        System.out.println();
        while (true) {
            System.out.println("[" + p.getName()
                    + "] Actions :[1] rolling Dice  [2] Display own Property   [3] load Save  [4] Save Game");
            try {
                option = sc.nextInt();
                if (option >= 1 && option <= playerActionList.length)
                    break;
                else
                    System.out.println("\nPlease enter the number  in range form 1 to 4\n");
            } catch (InputMismatchException sa) {
                System.out.println("Please enter a integer number");
                sc.next();
                continue;
            }
        }

        return (Command) Class.forName(playerActionList[(option - 1)]).getDeclaredConstructor().newInstance();

    }

}