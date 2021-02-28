import java.util.Scanner;

public class HumanBlackjackPlayer extends BlackjackPlayer {
    public boolean hit(
            BlackjackHand dealerHand, BlackjackHand playerHand
    ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println(" Player Hand:");
        playerHand.print();
        System.out.print("Hit? [y/n]: ");
        while (true) {
            switch (scanner.nextLine().toLowerCase()) {
                case "yes":
                case "y":
                    return true;
                case "no":
                case "n":
                    return false;
                default:
                    System.out.print("Please type y or n: ");
            }
        }
    }

    public void dealerHit(BlackjackHand dealerHand) {
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println("The dealer hit.");
    }

    public void playerBusts(BlackjackHand playerHand) {
        System.out.println(" Player Hand:");
        playerHand.print();
        System.out.println("You busted.");
        System.out.println("------------------------------------\n");
    }

    public void playerTies(
            BlackjackHand playerHand, BlackjackHand dealerHand
    ) {
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println(" Player Hand:");
        playerHand.print();
        System.out.println("You tied the dealer.");
        System.out.println("------------------------------------\n");
    }

    public void playerWins(
            BlackjackHand playerHand, BlackjackHand dealerHand
    ) {
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println(" Player Hand:");
        playerHand.print();
        System.out.println("You won.");
        System.out.println("------------------------------------\n");
    }

    public void dealerBusts(BlackjackHand dealerHand) {
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println("The dealer busted.");
        System.out.println("------------------------------------\n");
    }

    public void dealerWins(
            BlackjackHand dealerHand, BlackjackHand playerHand
    ) {
        System.out.println(" Dealer Hand:");
        dealerHand.print();
        System.out.println(" Player Hand:");
        playerHand.print();
        System.out.println("The dealer won.");
        System.out.println("------------------------------------\n");
    }
}
