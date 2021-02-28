// Written by Bobby George

public class GeorgeBobbyStrategy extends BlackjackStrategy {
    public static int one = 1;
    public static int two = 1;
    public static int three = 1;
    public static int four = 1;
    public static int five = 1;
    public static int six = 1;
    @Override
    public boolean hit(int handValue, int dealerHandValue, boolean soft) {
        if (soft) {
            return handValue < one || (dealerHandValue > two && handValue < three);
        } else {
            return handValue < four || (dealerHandValue > five && handValue < six);
        }
    }

    public String author() {
        return "Bobby G.";
    }

    public String playerName() {
        return "Bobby G's Strategy";
    }
}
