const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function calculateValue(hand) {
  let handValue = 0;
  let aces = false;
  for (const card of hand) {
    if (card.value > 10) {
      handValue += 10;
    } else {
      handValue += card.value;
    }
    if (card.value === 1) {
      aces = true;
    }
  }
  if (aces && handValue < 12) {
    handValue += 10;
  }
  return handValue;
}

function printHand(hand) {
  for (const card of hand) {
    let stringValue;
    switch (card.value) {
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
        stringValue = card.value;
    }

    let stringSuit;
    switch (card.suit) {
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

    console.log(` ${stringValue} of ${stringSuit}`);
  }
}

function* generateDeck() {
  for (let suit = 0; suit < 4; suit++) {
    for (let value = 1; value < 14; value++) {
      yield { suit, value };
    }
  }
}

function shuffle(array) {
  for (let i = 0; i < array.length - 1; i++) {
    const swap = Math.floor(Math.random() * (array.length - i) + i);
    [array[i], array[swap]] = [array[swap], array[i]];
  }
}

function askYOrN(question, callback) {
  rl.question(question, (answer) => {
    if (answer === "y") {
      callback(true);
    } else if (answer === "n") {
      callback(false);
    } else {
      console.log("Please type y or n.");
      askYOrN(question);
    }
  });
}

function askHit(dealerHand, playerHand, deck) {
  console.log("Dealer Hand:");
  printHand(dealerHand);
  console.log("Player Hand:");
  printHand(playerHand);

  askYOrN("Hit? [y/n]: ", (yes) => {
    if (yes) {
      playerHand.push(deck.pop());
      if (calculateValue(playerHand) > 21) {
        console.log("Player Hand:");
        printHand(playerHand);
        console.log("You busted.");
        gameOver();
      } else {
        askHit(dealerHand, playerHand, deck);
      }
    } else {
      dealerTurn(dealerHand, playerHand, deck);
    }
  });
}

function dealerTurn(dealerHand, playerHand, deck) {
  while (calculateValue(dealerHand) < 17) {
    dealerHand.push(deck.pop());
    console.log("The dealer hit.");
    console.log("Dealer Hand:");
    printHand(dealerHand);
  }

  const dealerHandValue = calculateValue(dealerHand);
  const playerHandValue = calculateValue(playerHand);
  if (dealerHandValue > 21) {
    console.log("The dealer busted.");
  } else {
    console.log("Player Hand:");
    printHand(playerHand);
    if (dealerHandValue > playerHandValue) {
      console.log("The dealer won.");
    } else if (playerHandValue > dealerHandValue) {
      console.log("You won.");
    } else {
      console.log("You tied the dealer.");
    }
  }
  gameOver();
}

function gameOver() {
  askYOrN("Play again? [y/n]: ", (yes) => {
    if (yes) {
      game();
    } else {
      rl.close();
    }
  });
}

function game() {
  const deck = [...generateDeck()];
  shuffle(deck);
  const dealerHand = [deck.pop()];
  const playerHand = [deck.pop(), deck.pop()];

  askHit(dealerHand, playerHand, deck);
}

game();
