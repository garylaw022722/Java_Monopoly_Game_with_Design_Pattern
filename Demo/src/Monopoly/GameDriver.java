package Monopoly;

import java.util.ArrayList;
import java.util.Stack;
import java.util.*;

public class GameDriver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Object> squareList = new ArrayList<>();
        squareList.add(new Go(0));
        squareList.add(new Property("Central", 800, 90, 1));
        squareList.add(new Property("Wan Chai", 700, 65, 2));
        squareList.add(new INCOME_TAX(3));
        squareList.add(new Property("Stanley", 600, 60, 4));
        squareList.add(new Visiting(5));// JUST Visting
        squareList.add(new Property("Shek O", 400, 10, 6));
        squareList.add(new Property("Mong Kok", 500, 40, 7));
        squareList.add(new Chance(8));
        squareList.add(new Property("Tsing Yi", 400, 15, 9));
        squareList.add(new FreePacking(10));
        squareList.add(new Property("Shatin", 700, 75, 11));
        squareList.add(new Chance(12));
        squareList.add(new Property("Tuen Mun", 400, 20, 13));
        squareList.add(new Property("Tai Po", 500, 25, 14));
        squareList.add(new GO_TO_Jail(15));// Go To jail
        squareList.add(new Property("Sai Kung", 400, 10, 16));
        squareList.add(new Property("Yuen Long", 400, 25, 17));
        squareList.add(new Chance(18));
        squareList.add(new Property("Tai O", 600, 25, 19));

        GameData gameDataSet = new GameData();
        Game game = null;
        GameFactory gamePackage = null;
        String[] option = { "Monopoly.LoadGameFactory", "Monopoly.newGameFactory" };
        while (true) {
            System.out.println("[1] loading Save , [2] start a New game");
            int num = 0;

            try {
                num = sc.nextInt();
                game = new Game(squareList);
                
                Object object = Class.forName(option[num-1]).newInstance(); 
                gamePackage =(GameFactory)object;
                gamePackage.execute(sc, game, gameDataSet);
            } catch (InputMismatchException sa) {
                System.out.println("Please enter a integer number");
                sc.next();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

}