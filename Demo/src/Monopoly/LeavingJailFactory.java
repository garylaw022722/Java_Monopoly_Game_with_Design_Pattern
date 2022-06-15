package Monopoly;

import java.util.Scanner;

public class LeavingJailFactory implements CommandFactory {
    public Command createCommand(Game game) throws Exception {
        String res = "";
        Scanner sc = new Scanner(System.in);
        Player p = game.getPlayerList().peek();

        if (p.getStatus().equals("in Jail")) {
            // payment
            System.out.println(p.getName()
                    + " ,Do you wanna to leaving the jail with payment ? [Y] Payment [N]  Draw double dice");
            while (true) {
                res = sc.next();
                if (res.toUpperCase().equals("Y")) {
                    return new LeavingJailCommand(true);
                } else if (res.toUpperCase().equals("N")) {
                    return new rollingDic_Command();
                } else {
                    System.out.println("Invalid Input ! Please Selected [Y] Payment  or [N] Draw double dice");
                }

            }
        }

        return new playerOptionFactory().createCommand(game);
    }

}