package com.example.flashcards.command;

import com.example.flashcards.models.*;

public class NewDeckCommand implements Command{
    private String name;
    private String description;
    private DeckContainer deckContainer;
    public NewDeckCommand(String name, String description){
        this.name=name;
        this.description=description;
    }
    public void execute(){
        deckContainer.addDeck(new Deck(name,description));
    }
}
