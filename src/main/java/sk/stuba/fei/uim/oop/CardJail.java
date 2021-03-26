package sk.stuba.fei.uim.oop;

public class CardJail extends Card {

    @Override
    public void action(Game game, int playerID) {
        System.out.println(mDescription);

        game.getPlayer(playerID).goToJail();
        game.getPlayer(playerID).setPosition(6);
    }

    public CardJail(){
        mDescription = "You have to go to Jail.";
    }
}
