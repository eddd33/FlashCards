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



    /**
     * Method newDeck is used to create a new deck,
     * check if it can be defined beforehand and
     * add it to the deck list.
     *
     * @param name
     * The name used to define a new deck, it must be unique
     * 
     * @param description
     * The description used to define the deck, it has no other purpose than
     * provide information for the user.
     */
    public void newDeck(String name, String description) {
        boolean testExist = false;
        for (Deck deck : decks) {
            if (deck.getName().equals(name)) testExist = true;
        }
        if (testExist) {
            recNewDeck(name, description, 1);
        } else {
            Deck deck = new Deck(name, description);
            decks.add(deck);
            setActiveDeck(deck);
        }
    }



    /**
     * Method recNewDeck is a recursive method,
     * used to create a new deck if the name exist already,
     * add it to the deck list.
     *
     * @param name
     * The name used to define a new deck, it must be unique
     *
     * @param description
     * The description used to define the deck, it has no other purpose than
     * provide information for the user.
     *
     * @param i
     * The integer used to change the name that's already used,
     * with this model : <name> (i)
     */
    public void recNewDeck(String name, String description, int i) {
        String newName = name + " (" + i + ")";
        boolean testExist = false;
        for (Deck deck : decks) {
            if (deck.getName().equals(newName)) testExist = true;
        }
        if (testExist) {
            recNewDeck(name, description, i+1);
        } else {
            Deck deck = new Deck(newName, description);
            decks.add(deck);
            setActiveDeck(deck);
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
