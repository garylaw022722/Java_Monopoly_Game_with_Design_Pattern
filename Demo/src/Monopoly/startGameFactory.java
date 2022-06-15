package Monopoly;

public class startGameFactory implements CommandFactory {

    public Command createCommand(Game game) throws Exception {

        return new startGameCommand();
    }

}
