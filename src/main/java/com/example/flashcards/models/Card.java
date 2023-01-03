package com.example.flashcards.models;

public class Card {

    private String question;
    private String answer;
    private Boolean twoSided;
    private int difficulty;

    public Card() {}



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
}
