public class MySimpleStrategy extends BlackjackStrategy {
    public boolean hit(
            int handValue, int dealerHandValue, boolean soft
    ) {
        return (
                (dealerHandValue == 11 && handValue < 21) ||
                        handValue < 17 ||
                        soft
        );
    }
}
