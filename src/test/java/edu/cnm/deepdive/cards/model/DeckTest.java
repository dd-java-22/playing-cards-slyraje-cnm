package edu.cnm.deepdive.cards.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class DeckTest {
Deck deck = new Deck();
  @Test
  void deckTest_size() {
    assertEquals(52, deck.size());
  }

  @Test
  void deckTest_shuffleNull() {
    assertThrows(NullPointerException.class, () -> deck.shuffle(null));
  }

  @Test
  void deckTest_dealEmpty() {
    for (int i = 0; i < 52; i++) {
      deck.deal();
    }

    assertThrows(NoSuchElementException.class, () -> deck.deal());

    }
}