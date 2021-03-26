package sk.stuba.fei.uim.oop;

public class Property extends Tile {
    private int mCost;
    private boolean mOwned;
    private String mOwnerName;
    private int mVisitingFee;

    public void resetOwner(){
        mOwned = false;
        mOwnerName = "";
    }

    public boolean isOwnedBy(String name){
        return name.equals(mOwnerName);
    }

    @Override
    public void steppedOn(Game game, int playerID) {
        if (!mOwned) {
            System.out.println("You arrived at a NOT OWNED property called " + mName + ".\nIt costs " + mCost + ". Do you want to buy it? (y/n)");
            if (App.readConsole.nextLine().equals("y")) {
                if (game.getPlayer(playerID).getMoney() < mCost) {
                    System.out.println("You do not have enough money to buy the property.");
                } else {
                    game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() - mCost);

                    mOwned = true;
                    mOwnerName = game.getPlayer(playerID).getName();
                }
            } else{
                System.out.println("You choose not to buy the property.");
            }

        } else if(!game.getPlayer(playerID).getName().equals(mOwnerName)){
            System.out.println("You arrived at an OWNED property called " + mName + ".\nThis property belongs to " + mOwnerName + ", so you have to pay a visiting fee. It will cost you " + mVisitingFee + "$");
            if (game.getPlayer(playerID).getMoney() < mVisitingFee) {
                System.out.println("You do not have enough money to pay the visiting fee.");
                game.getPlayer(playerID).lose(game);
            }
            else {
                game.getPlayer(playerID).setMoney(game.getPlayer(playerID).getMoney() - mVisitingFee);

                for(int i = 0; i < game.getPlayersCount(); i++)
                {
                    if(game.getPlayer(i).getName().equals(mOwnerName)) {
                        game.getPlayer(i).setMoney(game.getPlayer(i).getMoney() + mVisitingFee);
                    }
                }
            }
        } else {
            System.out.println("You arrived at your own property called " + mName);
        }
        System.out.println();
    }


    public Property(String name, int cost)
    {
        mName = name;
        mType = "property";

        mCost = cost;
        mOwned = false;
        mOwnerName = "";
        mVisitingFee = (int)(cost * 0.5);
    }
}
