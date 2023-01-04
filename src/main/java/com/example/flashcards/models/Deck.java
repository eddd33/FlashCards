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

    public Deck(String name) {
        this.name = name;
        author = "me";
        description = "Description";
        last_try = Calendar.getInstance();
        tagList = new ArrayList<>();
        cards = new ArrayList<>();
    }



    /**
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



    /**
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

    /**
     * Add :
     * The following methods are used for adding element to tag and card list.
     */

    public void addCard(Card card) {
        cards.add(card);
    }
}
