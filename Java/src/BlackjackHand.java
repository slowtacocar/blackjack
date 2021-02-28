public class BlackjackHand extends Hand {
    private int handValue;
    private boolean soft;

    public BlackjackHand(boolean dealer) {
        super(1);
        if (!dealer) {
            addCard();
        }
    }

    public BlackjackHand() {
        super(0);
    }

    private void computeValue() {
        handValue = 0;
        boolean aces = false;
        soft = false;
        for (int i = 0; i < numberOfCards(); i++) {
            PlayingCard card = nthCard(i);
            int value = card.getValue();
            if (value > 10) {
                handValue += 10;
            } else {
                handValue += value;
            }
            if (value == PlayingCard.ACE) {
                aces = true;
            }
        }
        if (aces && handValue < 12) {
            handValue += 10;
            soft = true;
        }
    }

    public void addCard(PlayingCard card) {
        super.addCard(card);
        computeValue();
    }

    public int handValue() {
        return handValue;
    }

    public boolean soft() {
        return soft;
    }
}
