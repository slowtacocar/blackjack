#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

struct Card
{
    int value;
    int suit;
};

void game();

int calculateValue(std::vector<Card> hand)
{
    int handValue = 0;
    bool aces = false;
    for (Card card : hand)
    {
        if (card.value > 10)
        {
            handValue += 10;
        }
        else
        {
            handValue += card.value;
        }
        if (card.value == 1)
        {
            aces = true;
        }
    }
    if (aces && handValue < 12)
    {
        handValue += 10;
    }
    return handValue;
}

void printHand(std::vector<Card> hand)
{
    for (Card card : hand)
    {
        std::string stringValue;
        switch (card.value)
        {
            case 1:
                stringValue = "Ace";
                break;
            case 13:
                stringValue = "King";
                break;
            case 12:
                stringValue = "Queen";
                break;
            case 11:
                stringValue = "Jack";
                break;
            default:
                stringValue = std::to_string(card.value);
        }

        std::string stringSuit;
        switch (card.suit)
        {
            case 0:
                stringSuit = "Hearts";
                break;
            case 1:
                stringSuit = "Spades";
                break;
            case 2:
                stringSuit = "Clubs";
                break;
            case 3:
                stringSuit = "Diamonds";
        }

        std::cout << " " << stringValue << " of " << stringSuit << std::endl;
    }
}

std::vector<Card> generateDeck()
{
    std::vector<Card> deck;
    deck.reserve(52);
    for (int suit = 0; suit < 4; suit++)
    {
        for (int value = 1; value < 14; value++)
        {
            Card card;
            card.value = value;
            card.suit = suit;
            deck.push_back(card);
        }
    }
    return deck;
}

bool askYOrN(std::string question)
{
    std::string response;
    std::cout << question;
    std::cin >> response;
    if (response == "y")
    {
        return true;
    }
    if (response == "n")
    {
        return false;
    }
    std::cout << "Please type y or n." << std::endl;
    return askYOrN(question);
}

void gameOver()
{
    if (askYOrN("Play again? [y/n]: "))
    {
        game();
    }
}

void dealerTurn(std::vector<Card> dealerHand, std::vector<Card> playerHand, std::vector<Card> deck)
{
    while (calculateValue(dealerHand) < 17)
    {
        dealerHand.push_back(deck.back());
        deck.pop_back();
        std::cout << "The dealer hit." << std::endl;
        std::cout << "Dealer Hand:" << std::endl;
        printHand(dealerHand);
    }

    int dealerHandValue = calculateValue(dealerHand);
    int playerHandValue = calculateValue(playerHand);
    if (dealerHandValue > 21)
    {
        std::cout << "The dealer busted." << std::endl;
    }
    else
    {
        std::cout << "Player Hand:" << std::endl;
        printHand(playerHand);
        if (dealerHandValue > playerHandValue)
        {
            std::cout << "The dealer won." << std::endl;
        }
        else if (playerHandValue > dealerHandValue)
        {
            std::cout << "You won." << std::endl;
        }
        else
        {
            std::cout << "You tied the dealer." << std::endl;
        }
    }
    gameOver();
}

void askHit(std::vector<Card> dealerHand, std::vector<Card> playerHand, std::vector<Card> deck)
{
    std::cout << "Dealer hand:" << std::endl;
    printHand(dealerHand);
    std::cout << "Player Hand:" << std::endl;
    printHand(playerHand);

    if (askYOrN("Hit? [y/n]: "))
    {
        playerHand.push_back(deck.back());
        deck.pop_back();
        if (calculateValue(playerHand) > 21)
        {
            std::cout << "Player Hand:" << std::endl;
            printHand(playerHand);
            std::cout << "You busted." << std::endl;
            gameOver();
        }
        else
        {
            askHit(dealerHand, playerHand, deck);
        }
    }
    else
    {
        dealerTurn(dealerHand, playerHand, deck);
    }
}

void game()
{
    std::vector<Card> deck = generateDeck();
    std::random_shuffle(deck.begin(), deck.end());
    std::vector<Card> playerHand;
    playerHand.push_back(deck.back());
    deck.pop_back();
    std::vector<Card> dealerHand;
    dealerHand.push_back(deck.back());
    deck.pop_back();
    dealerHand.push_back(deck.back());
    deck.pop_back();

    askHit(dealerHand, playerHand, deck);
}

int main()
{
    game();
}
