package Monopoly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GO_TO_Jail extends Square {
    int fine = 150;

    public GO_TO_Jail(int pos) {
        super(pos);
    }

    public void payFine(Player p) {
        p.paidMoney(this.fine);
    }

    public void addJailDraw(Player p, int drawNum) {
        p.getJail_Draw().add(drawNum);

    }

    public boolean isLeavingJai(int num1, int num2) {// assume rolling dic application
        boolean flag = false;
        if (num1 == num2)
            flag = true;
        return flag;
    }

    public void EmptyJailDraw(Player p) {
        ArrayList<Integer> current = p.getJail_Draw();
        current.removeAll(current);

    }

}
