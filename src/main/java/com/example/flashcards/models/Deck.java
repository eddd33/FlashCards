package com.example.flashcards.models;

import java.util.ArrayList;
import java.util.Calendar;

public class Deck {

    private String name;
    private String author;
    private String description;
    private Calendar last_try;
    private ArrayList<String> tagList;
    private ArrayList<Card> cards;

    private double best_score;
    private double last_score;

    public Deck(String name) {
        this.name = name;
        author = "me";
        description = "Description";
        last_try = Calendar.getInstance();
        tagList = new ArrayList<>();
        cards = new ArrayList<>();
    }



    /**
     * Method that check if a card is the deck.
     *
     * @return boolean :
     *      true if the card is in the deck.
     *      false if not.
     */
    public boolean isInDeck(Card card) {
        return cards.contains(card);
    }



    /*
     * Getter :
     * The following methods are used for returning component value.
     */

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Calendar getLast_try() {
        return last_try;
    }

    public int getCardIndex(Card card) {
        return cards.indexOf(card);
    }

    public double getBestScore() {
        return best_score;
    }

    public double getLastScore() {
        return last_score;
    }

    /*
     * Setter :
     * The following methods are used for setting component value.
     */

    public void setLast_try(Calendar last_try) {
        this.last_try = last_try;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList.clear();
        this.tagList.addAll(tagList);
    }

    public void setBestScore(double score) {
        this.best_score = score;
    }

    public void setLastScore(double score) {
        this.last_score = score;
    }

    /*
     * Add :
     * The following methods are used for adding element to list.
     */

    public void addCard(Card card) {
        cards.add(card);
        // ici on peut mettre un check d'unité ?
    }

    public void addCard(int index, Card card) {
        cards.add(index, card);
    }

    /*
     * Remove :
     * The following methods are used for removing element to list.
     */

    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Compared the best score of two decks.
     * @param comparedDeck
     * @return boolean :
     *      true if the calling deck's best score is higher than the comparedDeck best score.
     *      false if it's the other way around.
     */
    public boolean hasHigherBestScore(Deck comparedDeck){
        return this.getBestScore() > comparedDeck.getBestScore();
    }

}
