package de.frahm_net.flashcards;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by lars on 12.03.17.
 */

public class DeckTest {

    @Test
    public void testAddition() {
        Deck deck = new Deck("testDeck");
        deck.addCard(new FlashCard("Question", "Answer"));
        assertEquals("Failure - Size should be one", 1, deck.size());
    }

    @Test
    public void testGetLevel() {
        Deck deck = new Deck("testDeck");
        deck.addCard(new FlashCard("q1", "a1", 1));
        deck.addCard(new FlashCard("q2", "a2", 2));
        deck.addCard(new FlashCard("q3", "a3", 1));
        assertEquals("Failure - wrong number of cards", 2,deck.getCardsAtLevel(1).size());
    }

    @Test
    public void testTitle() {
        Deck deck = new Deck("Spanish");
        assertEquals("Failure - title should be Spanish", "Spanish", deck.getName());
    }

}
