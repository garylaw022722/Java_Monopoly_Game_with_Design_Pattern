package Monopoly;

import java.util.*;

public interface Command {
    public abstract void execute(Scanner sc, Game game, GameData gameData);
}
