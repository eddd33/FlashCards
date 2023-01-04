package com.example.flashcards.command;

import com.example.flashcards.models.*;
import com.example.flashcards.view.ViewState;

public class DuplicateDeckCommand implements Command{

    private Deck deck;
    private DeckContainer deckContainer;
    private ViewState viewState;


    public DuplicateDeckCommand(DeckContainer deckContainer, ViewState viewState, Deck deck) {
        this.deckContainer=deckContainer;
        this.viewState = viewState;
        this.deck=deck;
    }



    public void execute(){
        deckContainer.dupDeck(deck);
        viewState.changeScene(2);
    }
}
