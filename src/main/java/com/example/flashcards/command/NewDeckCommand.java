package com.example.flashcards.command;

import com.example.flashcards.models.*;
import com.example.flashcards.view.ViewState;

public class NewDeckCommand implements Command{

    private DeckContainer deckContainer;
    private ViewState viewState;


    public NewDeckCommand(DeckContainer deckContainer,ViewState viewState){
        this.deckContainer=deckContainer;
        this.viewState=viewState;
    }



    public void execute(){
        deckContainer.newDeck();
        viewState.changeScene(2);
    }
}
