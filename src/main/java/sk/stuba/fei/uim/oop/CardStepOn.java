package sk.stuba.fei.uim.oop;

public class CardStepOn extends Card {

    @Override
    public void action(Game game, int playerID) {
        System.out.println(mDescription);

        int lastTileIndex = game.getGameMap().length - 1;

        game.getPlayer(playerID).setPosition(lastTileIndex);
        game.getGameMap()[lastTileIndex].steppedOn(game, playerID);
    }

    public CardStepOn(){
        mDescription = "You have to step on Diamond Hills.";
    }
}
