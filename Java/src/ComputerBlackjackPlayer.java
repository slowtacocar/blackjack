public class ComputerBlackjackPlayer extends BlackjackPlayer {
    private final BlackjackStrategy strategy;

    public ComputerBlackjackPlayer(BlackjackStrategy theStrategy) {
        strategy = theStrategy;
    }

    public boolean hit(
            BlackjackHand dealerHand, BlackjackHand playerHand
    ) {
        return strategy.hit(
                playerHand.handValue(),
                dealerHand.handValue(),
                playerHand.soft()
        );
    }
}
