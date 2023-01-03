package com.example.flashcards.command;

import com.example.flashcards.view.ViewState;

public class ChangeSceneCommand implements Command{

    private int i;
    private ViewState viewState;

    public ChangeSceneCommand(ViewState viewState, int i) {
        this.viewState = viewState;
        this.i = i;
    }

    @Override
    public void execute() {
        viewState.changeScene(i);
    }
}
