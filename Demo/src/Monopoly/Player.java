package Monopoly;

import java.util.*;

public class Player {
    private String name;
    private int money;
    private String status;
    private Square currentPos;
    private Vector<Property> propList;
    private ArrayList<Integer> jailDraw;

    public Player(String name, int money, String status, Square currentPos) {
        this.name = name;
        this.money = money;
        this.status = status;
        this.currentPos = currentPos;
        propList = new Vector<>();
        this.jailDraw = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getJail_Draw() {
        return this.jailDraw;
    }

    public void setJail_Draw(ArrayList<Integer> drawJail) {
        this.jailDraw = drawJail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void paidMoney(int money) {
        this.money -= money;
    }

    public String getName() {
        return this.name;
    }

    public Vector<Property> getAllProperty() {
        return this.propList;
    }

    public void setAllProperty(Vector<Property> propertyList) {
        this.propList = propertyList;
    }

    public void addProperty(Property property) {
        this.propList.addElement(property);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Square getCurrentPos() {
        return this.currentPos;
    }

    public void setCurrentPos(Square currentPos) {
        this.currentPos = currentPos;
    }

    public int rollingDice() {
        return new Random().nextInt(6) + 1;
    }

    public boolean isBankRuptcy() {
        boolean flag = false;
        if (status.equals("bankruptcy") || money <= 0)
            flag = true;
        return flag;
    }
}