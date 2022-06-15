package Monopoly;

public class Go extends Square {
    int extraSalary = 1500;

    public Go(int pos) {
        super(pos);

    }

    public int getExtraSalary() {
        return this.extraSalary;
    }
}