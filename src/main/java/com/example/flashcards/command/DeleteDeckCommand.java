package com.example.flashcards.command;

import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;

public class DeleteDeckCommand implements Command{

    DeckContainer container;
    Deck deck;


    public DeleteDeckCommand(DeckContainer container, Deck deck){
        this.container = container;
        this.deck = deck;
    }



    public void execute(){
        container.supprDeck(deck);
    }
}
