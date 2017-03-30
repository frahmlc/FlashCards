package de.frahm_net.flashcards;

import java.util.*;
/**
 * Created by lars on 12.03.17.
 */

public class Deck {
    private String name;
    private ArrayList<FlashCard> cards;

    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<FlashCard>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<FlashCard> getCards() {
        return cards;
    }

    public void addCard(FlashCard card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public ArrayList<FlashCard> getCardsAtLevel(int level) {
        ArrayList<FlashCard> subset = new ArrayList<FlashCard>();
        for (FlashCard card : cards) {
            if (level == card.getLevel()) {
                subset.add(card);
            }
        }
        return subset;
    }
}
