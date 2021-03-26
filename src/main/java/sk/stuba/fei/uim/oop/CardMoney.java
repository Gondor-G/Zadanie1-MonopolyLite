package sk.stuba.fei.uim.oop;

public class CardMoney extends Card {

    @Override
    public void action(Game game, int playerID) {
        System.out.println(mDescription);

        game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() + 500);
    }

    public CardMoney(){
        mDescription = "The bank pays you 500$.";
    }
}
