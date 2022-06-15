package Monopoly;

public interface CommandFactory {

    public abstract Command createCommand(Game game) throws Exception;

}
