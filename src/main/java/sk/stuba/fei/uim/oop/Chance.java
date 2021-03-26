package sk.stuba.fei.uim.oop;

public class Chance extends Tile{

    @Override
    public void steppedOn(Game game, int playerID) {
        System.out.println("You landed on a Chance tile, you have to draw a chance card.");
        System.out.print("The Chance card says: ");

        game.getPlayer(playerID).drawCard(game, playerID);
    }

    public Chance()
    {
        mName = "Chance";
        mType = "chance";
    }
}
