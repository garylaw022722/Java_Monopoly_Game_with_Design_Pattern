package Monopoly;

public class Property extends Square {
    private String name;
    private int price;
    private Player owner;
    private int rent;

    public Property(String name, int price, int rent, int pos) {
        super(pos);
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.owner = null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getRent() {
        return this.rent;
    }

}
