package com.example.flashcards.command;

import com.example.flashcards.models.Deck;
import com.example.flashcards.models.DeckContainer;
import com.example.flashcards.view.ViewState;

import java.util.ArrayList;

public class ValidateInfoCardModCommand implements Command{

    private DeckContainer container;

    private String question;
    private String answer;
    private boolean twoSided;
    private ArrayList<String> tags;

    public ValidateInfoCardModCommand(DeckContainer container, String question, String answer, boolean twoSided, ArrayList<String> tags) {
        this.container = container;
        this.question = question;
        this.answer = answer;
        this.twoSided = twoSided;
        this.tags = new ArrayList<>();
        this.tags.addAll(tags);
    }

    @Override
    public void execute() {
        container.changeInfoActiveCard(question,answer,twoSided,tags);
    }
}
