package com.example.flashcards.models;

import java.util.ArrayList;

public class Card {

    private String question;
    private String answer;
    private boolean twoSided;
    private double difficulty;
    private ArrayList<String> tagList;

    /**
     * Constructor for a blank Card.
     * Difficulty is set to 1 by default.
     * TwoSided is set to false by default.
     */
    public Card() {
        this.difficulty = 1;
        twoSided = false;
        tagList = new ArrayList<>();
        question="";
        answer="";
    }


    /**
     * Constructor to copy a Card.
     * Difficulty is set to 1 by default.
     *
     * @param question
     * String of the question
     *
     * @param answer
     * String of the answer.
     *
     * @param twoSided
     * Boolean : if the card can be put both way.
     * That's mean that there is no question or answer but two thing with a relation.
     * For example vocabulary can be put this way.
     *
     * @param tags
     * ArrayList<String> list of all the tags of the card.
     */
    public Card(String question, String answer, boolean twoSided, ArrayList<String> tags) {
        this.question = question;
        this.answer = answer;
        this.twoSided = twoSided;
        this.difficulty = 1;
        tagList = new ArrayList<>();
        tagList.addAll(tags);
    }



    /**
     * Method that create a copy of the card.
     * For simplicity reason, it's better to have a methode that call the constructor here,
     * so that you don't have to get each variable then call the constructor.
     *
     * @return Card, a copy of the card but with the difficulty reset to 1.
     */
    public Card copy() {
        return new Card(question, answer, twoSided, tagList);
    }



    /*
     * Getter :
     * The following methods are used for returning component value.
     */
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean getTwoSided() {
        return twoSided;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }



    /*
     * Setter :
     * The following methods are used for setting component value.
     */

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTwoSided(boolean twoSided) {
        this.twoSided = twoSided;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }



    /*
     * Add :
     * The following methods are used for adding element to list.
     */

    public void addTag(String tag) {
        tagList.add(tag);
    }
}
