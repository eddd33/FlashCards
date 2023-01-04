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
        newDeck();      // temp
        newDeck();      // temp
        newDeck();      // temp
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
        notifyObserver();                                               //notifyObserver
    }

    public void supprCard(Card card) {
        if (activeDeck != null) {
            // ...
        }

        for (Deck deck : decks) {
            // ...
        }
        notifyObserver();                                               //notifyObserver
    }



    /**
     * Method newDeck is used to create a new deck,
     * check if it can be defined beforehand and
     * add it to the deck list.
     *
     *
     * The name used to define a new deck, it must be unique
     *
     *
     * The description used to define the deck, it has no other purpose than
     * provide information for the user.
     */
    public void newDeck() {
        String name = "New deck";
        boolean testExist = false;
        for (Deck deck : decks) {
            if (deck.getName().equals(name)) testExist = true;
        }
        if (testExist) {
            recNewDeck(1);
        } else {
            Deck deck = new Deck(name);
            decks.add(deck);
            setActiveDeck(deck);
            notifyObserver();                                               //notifyObserver
        }
    }



    /**
     * Method recNewDeck is a recursive method,
     * used to create a new deck if the name exist already,
     * add it to the deck list.
     *
     *
     * The name used to define a new deck, it must be unique
     *
     *
     * The description used to define the deck, it has no other purpose than
     * provide information for the user.
     *
     * @param i
     * The integer used to change the name that's already used,
     * with this model : <name> (i)
     */
    public void recNewDeck(int i) {
        String newName = "New deck (" + i + ")";
        boolean testExist = false;
        for (Deck deck : decks) {
            if (deck.getName().equals(newName)) testExist = true;
        }
        if (testExist) {
            recNewDeck(i+1);
        } else {
            Deck deck = new Deck(newName);
            decks.add(deck);
            setActiveDeck(deck);
            notifyObserver();                                               //notifyObserver
        }
    }


    public void dupDeck(Deck deck) {
        String name = deck.getName();
        boolean testExist = false;
        for (Deck d : decks) {
            if (d.getName().equals(name)) testExist = true;
        }
        if (testExist) {
            recNewDeck(1);
        } else {
            Deck d = new Deck(name);
            decks.add(deck);
            setActiveDeck(deck);
        }
        notifyObserver();                                               //notifyObserver
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

    public Deck getActiveDeck() {
        return activeDeck;
    }



    /*
     * Setter :
     * The following methods are used for setting component value.
     */

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }
}
