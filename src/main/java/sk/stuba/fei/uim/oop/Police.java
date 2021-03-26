package sk.stuba.fei.uim.oop;

public class Police extends Corner{

    @Override
    public void steppedOn(Game game, int playerID) {
        System.out.println("You landed on Police, you have to go to Jail.");

        game.getPlayer(playerID).goToJail();
        game.getPlayer(playerID).setPosition(6);
    }

    public Police()
    {
        mName = "Police";
        mType = "police";
    }
}
