package Monopoly;

import java.util.Random;
import java.math.*;

public class Chance extends Square {
    int lost = 0, gain = 1;

    public Chance(int pos) {
        super(pos);
    }

    public int getDrawAmount() {
        int rand = new Random().nextInt(2);
        if (rand == gain)
            return drawGain();
        else
            return drawLost();
    }

    public int drawGain() {
        int num = (int) (Math.random() * (200 - 10)) + 10;
        num = (int) (num / 10) * 10;
        return num;
    }

    public int drawLost() {
        int num = -(int) (Math.random() * (300 - 10)) + 10;
        num = (int) (num / 10) * 10;
        return num;
    }

}
