package Monopoly;

import java.util.*;

public class startGameCommand implements Command {
    int endGame = 100;

    public void execute(Scanner sc, Game game, GameData gameData) {
        GAME_INTERFACE gi = new GAME_INTERFACE(game);
        gi.displayGameBoard(game.getRound());
        Queue<Player> playerList = game.getPlayerList();
        gameLoop: while (game.getRound() <= endGame) {
            for (int playerTurn = 0; playerTurn < game.getPlayerList().size(); playerTurn++) {
                if (game.getPlayerList().peek().isBankRuptcy()) {
                    game.nextPlayerTerm();
                    continue;
                }
                try {
                    long numOf_bankRupt = playerList.stream().filter(player -> player.getStatus().equals("bankrupt"))
                            .count();
                    if (numOf_bankRupt == playerList.size() - 1) { // only one gamer
                        break gameLoop;
                    }
                    CommandFactory leaving_OR_Menu = new LeavingJailFactory(); // leavingFatory or playerOptionFactory
                    leaving_OR_Menu.createCommand(game).execute(sc, game, gameData);

                    Command purchase_Property = new purchaseCommand();
                    purchase_Property.execute(sc, game, gameData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                game.nextPlayerTerm();

            }
            game.setRound(game.getRound() + 1);

        }
        game.getWiners().forEach(player -> {
            System.out.print(player.getName() + " ");
        });
        System.out.println(" is Winners !\n");
        System.out.println("Remaing Amount");
        game.getWiners().forEach(player -> {
            System.out.println(player.getName() + " " + player.getMoney());
        });

        // clear all data of square since the square will be reused
        Property p = null;
        for (Object square : game.getSquareList()) {
            Square square_ = (Square) square;
            square_.getPlayerIn_Square().removeAllElements();
            if (square_ instanceof Property) {
                p = (Property) square_;
                p.setOwner(null);
            }
        }

    }
}
