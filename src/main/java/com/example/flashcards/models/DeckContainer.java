package com.example.flashcards.models;

import com.example.flashcards.view.Observer;
import com.example.flashcards.view.SubjectObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeckContainer implements SubjectObserver {

    private ArrayList<Deck> decks;
    private ArrayList<Card> cards;
    private ArrayList<Observer> listObs;
    private Deck activeDeck;
    private Card activeCard;
    private Set<String> deckTags;
    private Set<String> cardTags;


    public DeckContainer() {
        decks = new ArrayList<>();
        cards = new ArrayList<>();
        listObs = new ArrayList<>();
        deckTags = new HashSet<>();
        cardTags = new HashSet<>();

        newDeck();      // temp
        activeDeck.addTag("lol");
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
     */
    public void newDeck() {
        String name = getUniqueName("New deck");
        Deck deck = new Deck(name);
        decks.add(deck);

        Card newCard = new Card();
        deck.addCard(newCard);
        cards.add(newCard);

        setActiveDeck(deck);
        notifyObserver();                                               //notifyObserver
    }



    /**
     * Method dupDeck is used to duplicate a deck,
     * check if it can be defined beforehand and
     * add it to the deck list.
     *
     * @param deck
     * The deck used to duplicate from.
     */
    public void dupDeck(Deck deck) {
        String name = getUniqueName("Copy of " + deck.getName());
        Deck newDeck = new Deck(name);
        newDeck.setDescription(deck.getDescription());
        for (Card c : deck.getCards()) {
            newDeck.addCard(c);
        }
        decks.add(newDeck);
        setActiveDeck(newDeck);
        notifyObserver();                                               //notifyObserver
    }



    /**
     * Method getUniqueName is a used to check
     * if a name is already used.
     *
     * @param name
     * The name is the name proposed for the deck that need to be checked
     *
     * @return
     * Type de retour : String
     * String modified or not to be unique.
     */
    public String getUniqueName(String name) {
        boolean testExist = false;
        for (Deck newDeck : decks) {
            if (newDeck.getName().equals(name)) {
                testExist = true;
                break;
            }
        }
        if (testExist) {
            return recGetUniqueName(name,1);
        } else {
            return name;
        }
    }



    /**
     * Method getUniqueName is a recursive method
     * used to check if a name is already used.
     * The method is called by getUniqueName.
     *
     * @param name
     * The name is the name proposed for the deck that need to be checked
     *
     * @param i
     * The integer used to change the name that's already used,
     * with this model : <proposed name> (i)
     *
     * @return
     * Type de retour : String
     * String modified or not to be unique.
     */
    private String recGetUniqueName(String name, int i) {
        String newName = name + " (" + i + ")";
        boolean testExist = false;
        for (Deck newDeck : decks) {
            if (newDeck.getName().equals(newName)) {
                testExist = true;
                break;
            }
        }
        if (testExist) {
            return recGetUniqueName(name,i+1);
        } else {
            return newName;
        }
    }



    /**
     * Method is used to know if activeDeck is empty of cards
     *
     * @return
     * Type de retour : boolean
     * the active deck is empty of cards
     */
    public boolean activeDeckIsEmpty() {
        return activeDeck.getCards().isEmpty();
    }



    /**
     * Method is used to add a tag to a deck,
     * and put it in the set of tag for decks.
     *
     * @param tag
     * The string is the tag you want to add to the set.
     *
     * @param deck
     * The deck is where you want to add the tag.
     */
    public void addDeckTag(String tag, Deck deck) {
        deckTags.add(tag);
    }



    /**
     * Method is used to add a tag to a card,
     * and put it in the set of tag for cards.
     *
     * @param tag
     * The string is the tag you want to add to the set.
     *
     * @param card
     * The deck is where you want to add the tag.
     */
    public void addCardTag(String tag, Card card) {
        deckTags.add(tag);
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

    public Card getActiveCard() {
        return activeCard;
    }

    public Set<String> getDeckTags() {
        return deckTags;
    }

    public Set<String> getCardTags() {
        return cardTags;
    }

    /*
     * Setter :
     * The following methods are used for setting component value.
     */

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public void setActiveCard(Card activeCard) {
        this.activeCard = activeCard;
    }
}
