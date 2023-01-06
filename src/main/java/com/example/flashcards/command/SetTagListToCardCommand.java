package com.example.flashcards.command;

import com.example.flashcards.models.DeckContainer;

import java.util.ArrayList;

public class SetTagListToCardCommand implements Command {

    private DeckContainer container;
    private ArrayList<String> tags;

    public SetTagListToCardCommand(DeckContainer container, ArrayList<String> tags) {
        this.container = container;
        this.tags = new ArrayList<>();
        this.tags.addAll(tags);
    }

    @Override
    public void execute() {
        container.setActiveDeckTagList(tags);
    }
}
