package sk.stuba.fei.uim.oop;

public class Jail extends Corner {

    @Override
    public void steppedOn(Game game, int playerID) {
        System.out.println("You visited your friends in Jail.");
    }

    public Jail() {
        mName = "Jail";
        mType = "jail";
    }
}
