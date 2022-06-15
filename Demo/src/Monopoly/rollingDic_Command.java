package Monopoly;

import java.util.*;

public class rollingDic_Command implements Command {
    ArrayList<Object> squareList; // differnet to class diagrame
    int round;

    public void execute(Scanner sc, Game game, GameData gameData) {
        Square newSquare, oldSquare;
        int dicNum, nextPos = 0;
        Player p = game.getPlayerList().peek();
        GAME_INTERFACE gi = new GAME_INTERFACE(game);
        squareList = game.getSquareList();
        oldSquare = p.getCurrentPos();
        dicNum = p.rollingDice();
        String Main_message = p.getName() + " " + "Log : \nRolling Dice  : ";
        nextPos = oldSquare.getPos() + dicNum;
        round = game.getRound();

        if (nextPos > squareList.size() - 1) {
            nextPos %= squareList.size();
        }

        if (p.getStatus().equals("in Jail")) {
            GO_TO_Jail jail = (GO_TO_Jail) game.getSquareList().get(15);
            int additional_Draw = p.rollingDice();
            jail.addJailDraw(p, dicNum);
            jail.addJailDraw(p, additional_Draw);
            Main_message += dicNum + " " + additional_Draw;
            double maxIndex = Math.round((double) p.getJail_Draw().size() / 2.0);
            int jail_Round = (int) maxIndex; /// 1-3 and integer

            if (jail.isLeavingJai(dicNum, additional_Draw)) {
                new LeavingJailCommand(true).execute(sc, game, gameData);
            } else {
                if (jail_Round == 3) {
                    System.out.println("presss Enter to rolling Dice");
                    sc.next();
                    new LeavingJailCommand(false).execute(sc, game, gameData);
                } else {
                    gi.displayGameBoard(round);
                    gi.displayMessage(Main_message + "\n");
                }
            }
            return;
        }

        newSquare = (Square) squareList.get(nextPos);
        p.setCurrentPos(newSquare);
        newSquare.addPlayerIN_Square(p);
        oldSquare.removePlayerIN_Square(p);

        // update and display player log
        String message = triggerSquareAction(newSquare, p);
        gi.displayGameBoard(round);
        gi.displayMessage(Main_message + dicNum + "\n" + "\n" + message + "\nRemain Asset $" + p.getMoney() + "\n");

        if (p.getMoney() <= 0) {
            p.setStatus("bankrupt");
            System.out.println(p.getName() + " : You are loose");
        }

    }

    public String triggerSquareAction(Square square, Player p) {
        String message = "";
        if (square instanceof Property) {
            Property property = (Property) square;
            int rent = property.getRent();
            Player owner = property.getOwner();
            if (owner != null) {
                owner.addMoney(rent);
                p.paidMoney(rent);
                message = "Rent Charge : " + owner.getName() + " gain $" + rent + " ," + p.getName() + " loss $" + rent;
            }
        } else if (square instanceof Chance) {
            Chance chance = (Chance) square;
            p.setMoney(p.getMoney() + chance.getDrawAmount());
            message = p.getName() + " : draw chance amount : " + chance.getDrawAmount();

        } else if (square instanceof INCOME_TAX) {
            INCOME_TAX it = (INCOME_TAX) square;
            int charge = it.getTaxCharge(p.getMoney());
            p.paidMoney(charge);
            message = p.getName() + " is charged the income tax of $" + charge;
        } else if (square instanceof GO_TO_Jail) {
            Visiting jail = (Visiting) squareList.get(5);
            p.setStatus("in Jail");
            p.setCurrentPos(jail);
        }
        if (square instanceof Go && round > 0) {
            Go go = (Go) square;
            int extra = go.getExtraSalary();
            p.addMoney(extra);
            message = p.getName() + " gain extra $" + extra + " ! " + message;
        }
        return message;
    }

}