import random


def calculate_value(hand):
    hand_value = 0
    aces = False
    for card in hand:
        if card["value"] > 10:
            # King, queen, and jack have a value of 10
            hand_value += 10
        else:
            hand_value += card["value"]
        if card["value"] == 1:
            aces = True
    if aces and hand_value < 12:
        # Aces can be either 1 or 11
        hand_value += 10
    return hand_value


def print_hand(hand):
    for card in hand:
        if card["value"] == 1:
            string_value = "Ace"
        elif card["value"] == 13:
            string_value = "King"
        elif card["value"] == 12:
            string_value = "Queen"
        elif card["value"] == 11:
            string_value = "Jack"
        else:
            string_value = card["value"]

        if card["suit"] == 0:
            string_suit = "Hearts"
        elif card["suit"] == 1:
            string_suit = "Spades"
        elif card["suit"] == 2:
            string_suit = "Clubs"
        else:
            string_suit = "Diamonds"

        print("", string_value, "of", string_suit)


def generate_deck():
    for suit in range(4):
        for value in range(1, 14):
            yield {
                "suit": suit,
                "value": value
            }


def main():
    play_again = True
    while play_again:
        deck = list(generate_deck())
        random.shuffle(deck)
        dealer_hand = [deck.pop()]
        player_hand = [deck.pop(), deck.pop()]

        hit_again = True
        game_over = False
        while hit_again:
            print("Dealer Hand:")
            print_hand(dealer_hand)
            print("Player Hand:")
            print_hand(player_hand)

            invalid = True
            while invalid:
                response = input("Hit? [y/n]: ")
                if response == "y":
                    invalid = False
                    player_hand.append(deck.pop())
                    if calculate_value(player_hand) > 21:
                        hit_again = False
                        game_over = True
                        print("Player Hand:")
                        print_hand(player_hand)
                        print("You busted.")
                elif response == "n":
                    invalid = False
                    hit_again = False
                else:
                    print("Please type y or n.")

        if not game_over:
            while calculate_value(dealer_hand) < 17:
                dealer_hand.append(deck.pop())
                print("The dealer hit.")
                print("Dealer Hand:")
                print_hand(dealer_hand)

            dealer_hand_value = calculate_value(dealer_hand)
            player_hand_value = calculate_value(player_hand)
            if dealer_hand_value > 21:
                print("The dealer busted.")
            else:
                print("Player Hand:")
                print_hand(player_hand)
                if dealer_hand_value > player_hand_value:
                    print("The dealer won.")
                elif player_hand_value > dealer_hand_value:
                    print("You won.")
                else:
                    print("You tied the dealer.")

        invalid = True
        while invalid:
            response = input("Play again? [y/n]: ")
            if response == "y":
                invalid = False
            elif response == "n":
                invalid = False
                play_again = False
            else:
                print("Please type y or n.")


if __name__ == "__main__":
    main()
