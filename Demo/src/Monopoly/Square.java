package Monopoly;

import java.util.Vector;

public class Square {
    int pos;
    Vector<Player> playersInSquare;

    public Square(int pos) {
        this.pos = pos;
        playersInSquare = new Vector<>();

    }

    public int getPos() {
        return this.pos;
    }

    public void addPlayerIN_Square(Player p) {
        playersInSquare.addElement(p);
    }

    public void removePlayerIN_Square(Player p) {
        playersInSquare.removeElement(p);
    }

    public Vector<Player> getPlayerIn_Square() {

        return this.playersInSquare;
    }

    public void setPlayerIn_Square(Vector<Player> playersInSquare) {

        this.playersInSquare = playersInSquare;
    }

}
