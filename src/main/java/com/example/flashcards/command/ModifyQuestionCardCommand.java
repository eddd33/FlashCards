package com.example.flashcards.command;

import com.example.flashcards.models.*;
public class ModifyQuestionCardCommand implements Command{
    private Card card;
    private String question;
    public ModifyQuestionCardCommand(Card card, String question){
        this.question=question;
        this.card=card;
    }
    public void execute(){
        card.setQuestion(question);
        card.setDifficulty(1);
    }
}
