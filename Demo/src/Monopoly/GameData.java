package Monopoly;

import java.util.*;

public class GameData {
    private Vector<Memento> list_of_Data;

    public GameData() {
        list_of_Data = new Vector<>(4);
    }

    public void saveGame(Memento me) {
        list_of_Data.add(me);

    }

    public Memento loadSave(int index) {
        return list_of_Data.get(index);

    }

    public Vector<Memento> getAll_Data() {
        return this.list_of_Data;
    }

}
