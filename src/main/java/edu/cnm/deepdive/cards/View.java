package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import edu.cnm.deepdive.cards.service.Trick;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class View {

  private static final Comparator<Card> BLACK_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .thenComparing(Comparator.naturalOrder());
  private static final Comparator<Card> RED_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .reversed()
          .thenComparing(Comparator.naturalOrder());

  void perform () {
    System.out.println("Are you ready for a card trick?");
    Deck deck = new Deck();
    System.out.println("Here is the deck we are starting with.");
    System.out.println(deck);
    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);
    trick.perform();
    System.out.println("Now we will swap the cards.");
    int numSwaps = trick.swap();
    System.out.println("We swapped " + numSwaps + " cards.");
    Map<Color, List<Card>> result = trick.getResult();
    TrickResult representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));
    System.out.println("Here is the result of the trick.");
    System.out.println(representation);

  }

  private record TrickResult(List<Card> blackPile, List<Card> redPile) {

    @Override
    public String toString() {
      long redInRedCount = redPile
          .stream()
          .filter(card -> card.getColor() == Color.RED)
          .count();
      long blackInBlackCount = blackPile
          .stream()
          .filter(card -> card.getColor() == Color.BLACK)
          .count();
      return "Red Pile (" + redInRedCount + "): " + redPile +
          "\nBlack Pile (" + blackInBlackCount + "): " + blackPile;
    }
  }
}
