package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Game {
    private int mPlayersInGame;

    private Tile[] mGameMap;
    private ArrayList<Card> mCards;
    private ArrayList<Player> mPlayers;

    public int getPlayersInGame()
    {
        return mPlayersInGame;
    }

    public void setPlayersInGame(int num)
    {
        mPlayersInGame = num;
    }

    public Tile[] getGameMap()
    {
        return mGameMap;
    }

    public ArrayList<Card> getCards()
    {
        return mCards;
    }

    public int getPlayersCount()
    {
        return mPlayers.size();
    }

    public Player getPlayer(int playerID)
    {
        if(playerID < 0 || playerID >= mPlayers.size())
        {
            throw new GameExceptions("Player ID is out of range");
        }
        return mPlayers.get(playerID);
    }

    private ArrayList<Player> createPlayers()
    {
        System.out.println("How many players will play?");
        int playerCount = App.readConsole.nextInt();
        App.readConsole.nextLine();

        ArrayList<Player> players = new ArrayList<>();

        for(int i = 0; i < playerCount; i++)
        {
            System.out.println("Name of the " + (i + 1) + ". player: ");

            players.add(new Player(App.readConsole.nextLine(), i));

            //System.out.println((i+1) + ". player's name is: " + players.get(i).getName());
        }
        System.out.println();

        mPlayersInGame = players.size();

        return players;
    }

    private Tile[] createMap()
    {
        return new Tile[]{
                new Start(),
                new Property("Stoneside",  600), new Property("Summergate", 800), new Chance(), new Property("Westlands", 1000), new Property("Bayview Dock", 1200),
                new Jail(),
                new Property("Harbor Heights", 1400), new Property("Old Town", 1600), new Chance(), new Property("East Bank", 1800), new Property("Treetop Park", 2000),
                new TaxPayment(),
                new Property("Middletown", 2200), new Property("Seaview", 2400), new Chance(), new Property("Central City", 2600), new Property("Silver Harbor", 2800),
                new Police(),
                new Property("Riverside", 3000), new Property("Royal Court", 3200), new Chance(), new Property("Fortune Valley", 3600), new Property("Diamond Hills", 4000)
        };
    }

    private ArrayList<Card> createCards()
    {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new CardMoney());
        cards.add(new CardStepOn());
        cards.add(new CardForward());
        cards.add(new CardJail());
        cards.add(new CardGraffiti());

        return cards;
    }

    public boolean init()
    {
        mGameMap = createMap();
        mCards = createCards();
        mPlayers = createPlayers();

        return !mPlayers.isEmpty() && !mCards.isEmpty();
    }

    public void movePlayer(int steps, int playerID)
    {
        if ( steps <= 0) {
            return;
        }

        int newPosition = getPlayer(playerID).getPosition() + steps;

        if (newPosition > mGameMap.length - 1) {
            newPosition -= mGameMap.length;

            System.out.println("You passed the start. You get 1000$");
            getPlayer(playerID).setMoney(getPlayer(playerID).getMoney() + 1000);
        }
        getPlayer(playerID).setPosition(newPosition);
        mGameMap[getPlayer(playerID).getPosition()].steppedOn(this, playerID);
    }

    public void playGame(){

        while (mPlayersInGame > 1)
        {
            for(Player player : mPlayers)
            {
                if(player.didLose())
                {
                    continue;
                }
                System.out.println("-------------------------------------------------");
                System.out.println("It is " + player.getName() + "'s turn.\n\tMoney: " + player.getMoney() + "\n\tPosition: " + mGameMap[player.getPosition()].mName + "\n");
                System.out.println("Press ENTER to roll.");

                try
                {
                    App.readConsole.nextLine();
                }
                catch(Exception e)
                {
                    System.out.println("Error while waiting for ENTER.");
                    return;
                }

                try {
                    int numOfSteps = player.roll();
                    movePlayer(numOfSteps, player.getPlayerID());

                    if(mPlayersInGame == 1)
                    {
                        break;
                    }
                } catch (GameExceptions e){
                    System.out.println('\n' + e.toString());
                    return;
                }

                System.out.println();
            }
        }

        for (Player mPlayer : mPlayers) {
            if (!mPlayer.didLose()) {
                System.out.println("There are no other players left to compete with.\nThe WINNER is " + mPlayer.getName());
                break;
            }
        }
    }

    public Game(){
        mPlayersInGame = 0;
    }
}
