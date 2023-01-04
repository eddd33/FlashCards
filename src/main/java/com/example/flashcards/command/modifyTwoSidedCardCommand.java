package com.example.flashcards.command;
import com.example.flashcards.models.*;

public class modifyTwoSidedCardCommand implements Command{
    private Card card;
    private Boolean twoSided;
    public modifyTwoSidedCardCommand(Card card, Boolean twoSided){
        this.twoSided=twoSided;
        this.card=card;
    }
    public void execute(){
        card.setTwoSided(twoSided);
        card.setDifficulty(1);
    }
}