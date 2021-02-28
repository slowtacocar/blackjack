import java.util.Scanner;

public class PlayingCard {
    public static final int ACE = 1;
    public static final int KING = 13;
    public static final int QUEEN = 12;
    public static final int JACK = 11;

    public static final int HEARTS = 1;
    public static final int SPADES = 2;
    public static final int CLUBS = 3;
    public static final int DIAMONDS = 4;
    private static boolean random = true;
    private int suit;
    private int value;

    public PlayingCard(int sui, int val) {
        suit = sui;
        value = val;
    }

    public PlayingCard() {
        if (random) {
            suit = (int) (Math.random() * 4 + 1);
            value = (int) (Math.random() * 13 + 1);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a suit: ");
            boolean badInput = true;
            while (badInput) {
                badInput = false;
                switch (scanner.nextLine().toLowerCase()) {
                    case "hearts":
                        suit = 1;
                        break;
                    case "spades":
                        suit = 2;
                        break;
                    case "clubs":
                        suit = 3;
                        break;
                    case "diamonds":
                        suit = 4;
                        break;
                    default:
                        System.out.print("Enter a valid suit name:");
                        badInput = true;
                }
            }
            System.out.print("Enter a value: ");
            badInput = true;
            while (badInput) {
                badInput = false;
                String input = scanner.nextLine().toLowerCase();
                switch (input) {
                    case "ace":
                    case "a":
                        value = 1;
                        break;
                    case "king":
                    case "k":
                        value = 13;
                        break;
                    case "queen":
                    case "q":
                        value = 12;
                        break;
                    case "jack":
                    case "j":
                        value = 11;
                        break;
                    default:
                        int intInput = Integer.parseInt(input);
                        if (intInput >= 1 && intInput <= 13) {
                            value = intInput;
                        } else {
                            System.out.print("Enter a valid value:");
                            badInput = true;
                        }
                }
            }
        }
    }

    public static void setRandom(boolean ran) {
        random = ran;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        String stringValue = "";
        String stringSuit = "";
        switch (value) {
            case 1:
                stringValue = "A";
                break;
            case 13:
                stringValue = "K";
                break;
            case 12:
                stringValue = "Q";
                break;
            case 11:
                stringValue = "J";
                break;
            default:
                stringValue = Integer.toString(value);
        }
        switch (suit) {
            case 1:
                stringSuit = "Hearts";
                break;
            case 2:
                stringSuit = "Spades";
                break;
            case 3:
                stringSuit = "Clubs";
                break;
            case 4:
                stringSuit = "Diamonds";
        }

        return stringValue + " of " + stringSuit;
    }
}
