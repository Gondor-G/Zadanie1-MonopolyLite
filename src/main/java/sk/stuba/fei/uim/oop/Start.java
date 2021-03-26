package sk.stuba.fei.uim.oop;

public class Start extends Corner{

    @Override
    public void steppedOn(Game game, int playerID) {
        System.out.println("You landed on start. You get an extra 1000$");
        game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() + 1000);
    }

    public Start()
    {
        mName = "Start";
        mType = "start";
    }
}
