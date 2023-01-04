package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.ViewState;

import java.util.ArrayList;

public class ValidateInfoDeckModCommand implements Command{

    private DeckContainer container;
    private ViewState viewState;
    private String name;
    private String author;
    private String description;
    private ArrayList<String> tags;


    public ValidateInfoDeckModCommand(DeckContainer container, ViewState viewState, String name, String author, String description, ArrayList<String> tags) {
        this.container = container;
        this.viewState = viewState;
        this.name = name;
        this.author = author;
        this.description = description;
        this.tags = new ArrayList<>();
        this.tags.addAll(tags);
    }



    @Override
    public void execute() {
        if (!container.getActiveDeck().getName().equals(name)) {
            container.getActiveDeck().setName(container.getUniqueName(name));
        }
        container.getActiveDeck().setAuthor(author);
        container.getActiveDeck().setDescription(description);
        for (String t : tags) {
            container.addDeckTag(t);
        }
        container.getActiveDeck().setTagList(tags);
        container.notifyObserver();
        viewState.changeScene(2);
    }
}
