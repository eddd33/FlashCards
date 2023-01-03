package com.example.flashcards.command;

import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;

public class DeleteDeck {
    DeckContainer deckContainer;
    public DeleteDeck(String name, String description){
    }
    public void execute(){
        deckContainer.DeleteDeck();
    }
}
