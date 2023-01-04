package com.example.flashcards.command;

import com.example.flashcards.models.Card;

public class ModifyCardCommand implements Command{

    private String question;
    private String answer;
    private Boolean twoSided;
    private int difficulty;
    private Card card;


    public ModifyCardCommand(Card card, String question,String answer, Boolean twoSided,int difficulty){
        this.card=card;
        this.question=question;
        this.answer=answer;
        this.twoSided=twoSided;
        this.difficulty=difficulty;
    }



    public void execute(){
        card.setAnswer(answer);
        card.setQuestion(question);
        card.setDifficulty(difficulty);
        card.setTwoSided(twoSided);

    }
}
