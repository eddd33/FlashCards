package com.example.flashcards.models;

import java.util.ArrayList;

public class Card {

    private String question;
    private String answer;
    private Boolean twoSided;
    private int difficulty;
    private ArrayList<String> tagList;

    public Card() {
        tagList = new ArrayList<>();
        this.difficulty=1;
    }



    /**
     * Getter :
     * The following methods are used for returning component value.
     */
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getTwoSided() {
        return twoSided;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }



    /**
     * Setter :
     * The following methods are used for setting component value.
     */

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTwoSided(Boolean twoSided) {
        this.twoSided = twoSided;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }



    /**
     * Add :
     * The following methods are used for adding element to tag and card list.
     */

    public void addTag(String tag) {
        tagList.add(tag);
    }
}
