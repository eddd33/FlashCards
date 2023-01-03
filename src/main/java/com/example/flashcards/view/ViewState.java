package com.example.flashcards.view;

import com.example.flashcards.controller.SceneController;

import java.util.ArrayList;

public class ViewState implements SubjectObserver {

    private int state;
    private SceneController controller;
    public ArrayList<Observer> observers = new ArrayList<Observer>();

    public ViewState(SceneController controller) {
        state = 0;
        this.controller = controller;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : this.observers) {
            o.update();
        }
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void changeScene(int state){
        controller.changeScene(state);
        this.state=state;
    }
}
