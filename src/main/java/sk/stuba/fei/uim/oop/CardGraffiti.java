package sk.stuba.fei.uim.oop;

public class CardGraffiti extends Card {

    @Override
    public void action(Game game, int playerID) {
        System.out.println(mDescription);

        int graffitiCounter = 0;

        for(int i = 0; i < game.getGameMap().length; i++)
        {
            if(game.getGameMap()[i].mType.equals("property") && ((Property)game.getGameMap()[i]).isOwnedBy(game.getPlayer(playerID).getName()))
            {
                graffitiCounter++;
            }
        }

        game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() - (graffitiCounter * 50));
    }

    public CardGraffiti(){
        mDescription = "You have to pay 50 after every property you own to remove graffiti.";
    }
}
