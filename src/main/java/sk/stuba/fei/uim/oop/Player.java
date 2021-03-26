package sk.stuba.fei.uim.oop;

import java.util.Random;

public class Player {
    private final String mName;
    private final int mPlayerID;
    private int mMoney;
    private int mPosition;
    private int mRoundsLeftImprisoned;
    private boolean mLost;

    public String getName()
    {
        return mName;
    }

    public int getPlayerID()
    {
        return mPlayerID;
    }

    public int getMoney()
    {
        return mMoney;
    }

    public void setMoney(int money)
    {
        mMoney = money;
    }

    public int getPosition()
    {
        return mPosition;
    }

    public void setPosition(int position)
    {
        mPosition = position;
    }

    public void goToJail()
    {
        mRoundsLeftImprisoned = 3;
    }

    public boolean didLose()
    {
        return mLost;
    }

    public void lose(Game game)
    {
        game.setPlayersInGame(game.getPlayersInGame() - 1);
        mLost = true;

        System.out.println("We are sorry, but " + mName + ", you have lost the game. Your properties had been given back to the bank.");

        for(int i = 0; i < 24; i++)
        {
            if(game.getGameMap()[i].mType.equals("property") && ((Property)game.getGameMap()[i]).isOwnedBy(mName))
            {
                ((Property)game.getGameMap()[i]).resetOwner();
            }
        }
    }

    public void drawCard(Game game, int playerID)
    {
        game.getCards().get(0).action(game, playerID);

        //Draw first card and put to bottom.
        Card picked = game.getCards().get(0);
        game.getCards().remove(0);
        game.getCards().add(picked);
    }

    public int roll()
    {
        if(mRoundsLeftImprisoned != 0)
        {
            mRoundsLeftImprisoned--;

            if(mRoundsLeftImprisoned != 0)
            {
                System.out.println("You are in prison, you have to wait " + mRoundsLeftImprisoned + " more rounds.");
            }
            else
            {
                System.out.println("For now you are still in prison, but next round you are free to go.");
            }
            return 0;
        }

        Random rand = new Random();

        int numOfSteps = rand.nextInt(6) + 1;

        System.out.println("You have rolled: " + numOfSteps);

        return numOfSteps;
    }

    public Player(String name, int playerID) {
        mName = name;
        mPlayerID = playerID;
        mMoney = 10000;
        mPosition = 0;
        mRoundsLeftImprisoned = 0;
        mLost = false;
    }
}