package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;

public class DeleteDeckCommand implements Command{
    DeckContainer deckContainer;
    public DeleteDeckCommand(String name, String description){
    }
    public void execute(){
        //deckContainer.DeleteDeck();
    }
}
