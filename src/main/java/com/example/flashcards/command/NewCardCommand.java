package com.example.flashcards.command;

import com.example.flashcards.models.Card;
import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;

public class NewCardCommand implements Command{
    private DeckContainer deckContainer;
    public NewCardCommand(DeckContainer deckContainer){
        this.deckContainer=deckContainer;
    }
    public void execute(){
        deckContainer.newCard();
    }
}
