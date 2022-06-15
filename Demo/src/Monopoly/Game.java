package Monopoly;


import java.sql.Timestamp;
import java.util.*;

public class Game {

    private int round = 1;
    private ArrayList<Object> squareList;
    private Queue<Player> playerList;
    Timestamp saveTime;

    public Game(ArrayList<Object> squareList) {
        this.squareList = squareList;
        this.playerList = new LinkedList<>();
    }

    public Game() {

    }

    public void addPlayer(Player p) {
        this.playerList.add(p);
    }

    public void nextPlayerTerm() {
        Player current = playerList.poll();
        playerList.add(current);
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setPlayerList(LinkedList<Player> j) {
        this.playerList = j;
    }

    public int getRound() {
        return this.round;
    }

    public Queue<Player> getPlayerList() {
        return this.playerList;
    }

    public ArrayList<Object> getSquareList() {
        return this.squareList;
    }

    public void setSaveTime(Timestamp time) {
        this.saveTime = time;
    }

    public Timestamp getSaveTime() {
        return this.saveTime;
    }

    public void setSquareList(ArrayList<Object> squList) {
        this.squareList = squList;
    }

    public ArrayList<Player> getWiners() {
        ArrayList<Player> winnerList = new ArrayList<Player>();
        int max = 0;
        Player winner = null;

        for (Player p : this.getPlayerList()) { /// phase 1 to get most money of player
            if (!p.getStatus().equals("bankrupt")) {
                if (max <= p.getMoney()) {
                    max = p.getMoney();
                    winner = p;
                }
            }
        }
        winnerList.add(winner);
        for (Player p : this.getPlayerList()) { /// phase 1 to get most money of player
            if (p.getMoney() == winner.getMoney() && p != winner)
                winnerList.add(p);
        }

        return winnerList;
    }

}
