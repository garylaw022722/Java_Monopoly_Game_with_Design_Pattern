package Monopoly;

import java.util.*;

public class purchaseCommand implements Command {
    public void execute(Scanner sc, Game game, GameData gameData) {
        String ans = "";
        Player p = game.getPlayerList().peek();

        if (p.getCurrentPos() instanceof Property) {

            Property property = (Property) p.getCurrentPos();
            String property_Name = property.getName();
            if (property.getOwner() != null)
                return;

            System.out.println("[" + p.getName() + "] ,Do you want to purchase  $" + property.getPrice() + " of "
                    + property_Name + " (Rent: $" + property.getRent() + ") ? [Y] Purchase ,[N] Skip ");

            while (true) {

                ans = sc.next().toUpperCase();
                if (ans.equals("Y") || ans.equals("N"))
                    break;
                else
                    System.out.println("Invalid Input ! Please Selected [Y] Purchase  or [N] Skip");

            }

            if (ans.equals("Y")) {
                int price = property.getPrice();
                if (p.getMoney() < price) {
                    System.out.println("Not enough Money to purchased ");
                } else {
                    property.setOwner(p);
                    p.paidMoney(price);
                    p.addProperty(property);
                    System.out.println(property_Name + " are purchased by " + p.getName());
                    System.out.print(p.getName() + " Remain Asset : $" + p.getMoney());
                    System.out.println();
                }

            } else if (ans.equals("N")) {
                return;
            }

        }

    }

}
