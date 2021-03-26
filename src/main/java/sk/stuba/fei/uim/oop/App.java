package sk.stuba.fei.uim.oop;

import java.util.Scanner;

public class App {
    public static final Scanner readConsole = new Scanner(System.in);

    public static void main(String[] args)
    {
        Game game = new Game();

        if(game.init())
        {
            game.playGame();
        }
    }
}
