package Monopoly;

import java.util.*;

public class LeavingJailCommand implements Command {
    boolean isDraw_To_Leave;

    public LeavingJailCommand(boolean flag) {
        isDraw_To_Leave = flag;

    }

    public void execute(Scanner sc, Game game, GameData gameData) {
        String res = "", msg = "", squareMsg = "", Log = "";
        int first, sec, round, size, oldPosition, newPosition, accDic_Num = 0;
        Player player = game.getPlayerList().peek();
        GO_TO_Jail jail = (GO_TO_Jail) game.getSquareList().get(15);
        Log = player.getName() + " " + "Log : \n";

        if (!isDraw_To_Leave) {
            if (player.getMoney() > jail.fine) {
                jail.payFine(player);
                msg = "paid with fine of " + jail.fine;
            } else {
                System.out.println("No enough money to leval from the jail");
                try {
                    new LeavingJailFactory().createCommand(game);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {

        }
        round = game.getRound();
        size = player.getJail_Draw().size();
        if (size > 0) {
            first = player.getJail_Draw().get(size - 1);
            sec = player.getJail_Draw().get(size - 2);
            Log += "Rolling Dice  : " + first + " " + sec;
            accDic_Num = first + sec;
        }

        Square oldPos = player.getCurrentPos();
        oldPosition = oldPos.getPos();
        newPosition = oldPosition + accDic_Num;
        if (newPosition > game.getSquareList().size() - 1) {
            newPosition %= game.getSquareList().size();
        }

        Square newPos = (Square) game.getSquareList().get(newPosition);
        newPos.addPlayerIN_Square(player);
        player.setCurrentPos(newPos);
        jail.EmptyJailDraw(player); // clear dice num
        jail.removePlayerIN_Square(player); //
        player.setStatus("Gaming"); // leaving the jail
        GAME_INTERFACE gi = new GAME_INTERFACE(game);
        rollingDic_Command rollingDic = new rollingDic_Command();
        rollingDic.squareList = game.getSquareList();
        squareMsg = rollingDic.triggerSquareAction(newPos, player);

        gi.displayGameBoard(round);
        gi.displayMessage(Log + "\n" + player.getName() + "  leave from the jail " + msg + "\n" + squareMsg);

    }

}