package com.example.flashcards.models;

import java.util.ArrayList;

public class DeckContainer {

    private ArrayList<Deck> decks;
    private ArrayList<Card> cards;

    public DeckContainer() {
        decks = new ArrayList<>();
        cards = new ArrayList<>();
    }



    /**
     * Getter :
     * The following methods are used for returning component value.
     */

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }



    /**
     * Setter :
     * The following methods are used for setting component value.
     */



    /**
     * Add :
     * The following methods are used for adding element to deck and card list.
     */

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void addCard(Card card) {
        cards.add(card);
    }


}
