package sk.stuba.fei.uim.oop;

public class CardForward extends Card {

    @Override
    public void action(Game game, int playerID) {
        System.out.println(mDescription);

        game.movePlayer(4, playerID);
    }

    public CardForward(){
        mDescription = "You have to step forward 4 tiles.";
    }
}
