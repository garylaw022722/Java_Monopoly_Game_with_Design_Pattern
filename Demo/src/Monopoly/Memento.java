package Monopoly;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.sql.Timestamp;

public class Memento {
    private Game backUp;
    private int round;
    private ArrayList<Object> squareList;
    private Queue<Player> playerList;
    private Timestamp saveTime;

    public Memento(Game game) {
        backUp = game;
        this.round = game.getRound();
        this.squareList = new ArrayList<Object>();
        this.playerList = new LinkedList<Player>();
        // Player

        for (Player player : backUp.getPlayerList()) { // personal Data;
            ArrayList<Integer> drawJail = new ArrayList<>();
            Vector<Property> playerProperties = new Vector<>();
            Player temPlayer = new Player(player.getName(), player.getMoney(), player.getStatus(),
                    player.getCurrentPos());
            for (Property property : player.getAllProperty()) { // drawDic, property
                playerProperties.addElement(property);
            }
            for (int s : player.getJail_Draw()) { // drawDic, property
                drawJail.add(s);
            }

            temPlayer.setAllProperty(playerProperties); // property
            temPlayer.setJail_Draw(drawJail); // draw
            this.playerList.add(temPlayer);

            System.out.println(temPlayer.getName() + " Money" + temPlayer.getMoney());
        }

        // Square

        for (int s = 0; s < backUp.getSquareList().size(); s++) {
            Square square_Ori = (Square) backUp.getSquareList().get(s);

            Square emptySquare = new Square(s); // emty square
                                                // store player location
            for (Player ori : square_Ori.getPlayerIn_Square()) {
                for (Player p : playerList) {
                    if (p.getName().equals(ori.getName()))
                        emptySquare.addPlayerIN_Square(p);
                }

            }
            if (square_Ori instanceof Property) { // store property
                Property property = (Property) square_Ori;
                Property temp = new Property(property.getName(), property.getPrice(), property.getRent(), s);
                temp.setOwner(property.getOwner());
                temp.setPlayerIn_Square(emptySquare.getPlayerIn_Square());
                this.squareList.add(temp);
            } else
                this.squareList.add(emptySquare);

        }

        ///

        this.saveTime = game.getSaveTime();

    }

    public Timestamp getSaveTime() {
        return backUp.saveTime;
    }

    public void restore() {
        backUp.setPlayerList((LinkedList<Player>) this.playerList);
        backUp.saveTime = this.saveTime;
        backUp.setSaveTime(this.saveTime);

        for (Player e : backUp.getPlayerList()) {
            System.out.println(e.getName() + " Money" + e.getMoney());
        }

        for (Player e : this.playerList) {
            for (Player ori : backUp.getPlayerList()) {
                if (e.getName().equals(ori.getName())) {
                    ori.setMoney(e.getMoney());
                    ori.setCurrentPos(e.getCurrentPos());
                    ori.setAllProperty(e.getAllProperty());
                }
            }
        }

        for (int s = 0; s < this.squareList.size(); s++) { // Read saveData
            Square square = (Square) this.squareList.get(s); // emptySquare
            Square backUpSquare = (Square) backUp.getSquareList().get(s);

            Vector<Player> playerInSquare_BackUp = square.getPlayerIn_Square();
            if (square instanceof Property) {
                Property tempPro = (Property) square;
                Player temPlayer = tempPro.getOwner();
                Property original_Property = (Property) backUpSquare;
                original_Property.setOwner(temPlayer);
            }

            Vector<Player> lm = new Vector<>();
            for (Player w : playerInSquare_BackUp) {
                for (Player temp : this.playerList) {
                    if (w.getName().equals(temp.getName())) {
                        lm.add(w);
                    }

                }

            }

            backUpSquare.setPlayerIn_Square(lm);

        }

        System.out.println("SAASCALSA");
        backUp.setRound(this.round);
    }
}
