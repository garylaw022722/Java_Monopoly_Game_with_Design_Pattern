package Monopoly;

public class INCOME_TAX extends Square {
    double taxRate = 0.1;

    public INCOME_TAX(int pos) {
        super(pos);
    }

    public double getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public int getTaxCharge(int totalMoney) {
        totalMoney = (int) (totalMoney * taxRate);
        return (totalMoney / 10) * 10;

    }
}
