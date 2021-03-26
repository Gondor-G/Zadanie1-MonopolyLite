package sk.stuba.fei.uim.oop;

public class TaxPayment extends Corner{

    @Override
    public void steppedOn(Game game, int playerID) {
        System.out.println("You arrived at Tax Payment, you have to pay 200$ to the bank.");
        if (game.getPlayer(playerID).getMoney() < 200) {
            System.out.println("You do not have enough money to pay the tax.");
            game.getPlayer(playerID).lose(game);
        }
        else {
            game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() - 200);
        }
    }

    public TaxPayment()
    {
        mName = "Tax Payment";
        mType = "taxPayment";
    }
}
