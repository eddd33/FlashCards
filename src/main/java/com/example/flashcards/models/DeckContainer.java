package com.example.flashcards.models;

import com.example.flashcards.view.Observer;
import com.example.flashcards.view.SubjectObserver;

import java.util.ArrayList;

public class DeckContainer implements SubjectObserver {

    private ArrayList<Deck> decks;
    private ArrayList<Card> cards;
    private ArrayList<Observer> listObs;

    public DeckContainer() {
        decks = new ArrayList<>();
        cards = new ArrayList<>();
        listObs = new ArrayList<>();
    }

    /**
     * SubjectObserver :
     * The following methods are used implementing the SubjectObserver interface.
     */

    @Override
    public void addObserver(Observer o) {
        listObs.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : listObs) {
            o.update();
        }
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

    /*public void deleteDeck(Deck deck) {
        decks.remove(deck);
    }*/
}
