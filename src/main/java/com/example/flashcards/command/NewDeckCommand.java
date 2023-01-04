package com.example.flashcards.command;

import com.example.flashcards.models.*;
import com.example.flashcards.view.ViewState;

public class NewDeckCommand implements Command{
    private DeckContainer deckContainer;
    private String titre;
    private String description;
    ViewState viewState;
    public NewDeckCommand(DeckContainer deckContainer,ViewState viewState,String titre,String description){
        this.deckContainer=deckContainer;
        this.viewState=viewState;
        this.titre=titre;
        this.description=description;
    }
    public void execute(){
        deckContainer.newDeck(titre,description);
        viewState.changeScene(4);
    }
}
