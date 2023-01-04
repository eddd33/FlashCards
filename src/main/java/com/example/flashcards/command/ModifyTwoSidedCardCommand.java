package com.example.flashcards.command;
import com.example.flashcards.models.*;

public class ModifyTwoSidedCardCommand implements Command{
    private Card card;
    private Boolean twoSided;
    public ModifyTwoSidedCardCommand(Card card, Boolean twoSided){
        this.twoSided=twoSided;
        this.card=card;
    }
    public void execute(){
        card.setTwoSided(twoSided);
        card.setDifficulty(1);
    }
}