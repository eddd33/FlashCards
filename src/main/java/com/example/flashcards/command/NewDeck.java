package com.example.flashcards.command;

import com.example.flashcards.models.*;

import java.util.ArrayList;
import java.util.Calendar;

public class NewDeck implements Command{
    String name;
    String description;
    DeckContainer deckContainer;
    public NewDeck(String name, String description){
        this.name=name;
        this.description=description;
    }
    public void execute(){
        deckContainer.addDeck(new Deck(name,description));
    }
}
