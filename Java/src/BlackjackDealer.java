public class BlackjackDealer {
    public double playBlackjack(
            BlackjackPlayer player, int numGames
    ) {
        double score = 0;
        for (int i = 0; i < numGames; i++) {
            BlackjackHand dealerHand = new BlackjackHand(true);
            BlackjackHand playerHand = new BlackjackHand(false);
            boolean hitAgain = true;
            boolean gameOver = false;
            while (hitAgain) {
                if (player.hit(dealerHand, playerHand)) {
                    playerHand.addCard();
                    if (playerHand.handValue() >= 22) {
                        hitAgain = false;
                        gameOver = true;
                        player.playerBusts(playerHand);
                    }
                } else {
                    hitAgain = false;
                }
            }
            if (!gameOver) {
                while (dealerHand.handValue() < 17) {
                    dealerHand.addCard();
                    player.dealerHit(dealerHand);
                }
                if (dealerHand.handValue() >= 22) {
                    player.dealerBusts(dealerHand);
                    score++;
                } else if (dealerHand.handValue() > playerHand.handValue()) {
                    player.dealerWins(dealerHand, playerHand);
                } else if (playerHand.handValue() > dealerHand.handValue()) {
                    player.playerWins(playerHand, dealerHand);
                    score++;
                } else {
                    player.playerTies(playerHand, dealerHand);
                    score += 0.5;
                }
            }
        }
        return score / numGames;
    }
}
