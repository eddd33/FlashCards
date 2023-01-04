package com.example.flashcards.models;

import com.example.flashcards.view.Observer;
import com.example.flashcards.view.SubjectObserver;

import java.util.ArrayList;

public class DeckContainer implements SubjectObserver {

    private ArrayList<Deck> decks;
    private ArrayList<Card> cards;
    private ArrayList<Observer> listObs;
    private Deck activeDeck;

    public DeckContainer() {
        decks = new ArrayList<>();
        cards = new ArrayList<>();
        listObs = new ArrayList<>();
    }

    /*
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
     * Method newCard is used to create a new card
     * and put it into the list cards
     * and call the addCard method of the activeDeck
     */
    public void newCard() {
        if (activeDeck != null) {
            Card card = new Card();
            cards.add(card);
            activeDeck.addCard(card);
        }
    }

    public void supprCard(Card card) {
        if (activeDeck != null) {
            // ...
        }

        for (Deck deck : decks) {
            // ...
        }
    }



    /*
     * Getter :
     * The following methods are used for returning component value.
     */

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }



    /*
     * Setter :
     * The following methods are used for setting component value.
     */
}
