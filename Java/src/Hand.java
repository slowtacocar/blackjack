import java.util.ArrayList;

public class Hand {
    private final ArrayList<PlayingCard> cards;

    public Hand(int initialCards) {
        cards = new ArrayList<PlayingCard>();
        for (int i = 0; i < initialCards; i++) {
            addCard();
        }
    }

    public void addCard() {
        addCard(new PlayingCard());
    }

    public void addCard(PlayingCard card) {
        cards.add(card);
    }

    public int numberOfCards() {
        return cards.size();
    }

    public PlayingCard nthCard(int index) {
        return cards.get(index);
    }

    public void print() {
        for (PlayingCard card : cards) {
            System.out.println(card);
        }
    }
}
