package com.example.flashcards.command;

import com.example.flashcards.models.*;
public class modifyAnswerCardCommand implements Command{
    private Card card;
    private String answer;
    public modifyAnswerCardCommand(Card card, String answer){
        this.answer=answer;
        this.card=card;
    }
    public void execute(){
        card.setAnswer(answer);
        card.setDifficulty(1);
    }
}
