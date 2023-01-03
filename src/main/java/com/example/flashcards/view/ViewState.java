package com.example.flashcards.view;

import com.example.flashcards.controller.SceneController;
import javafx.scene.Scene;

import java.util.ArrayList;

public class ViewState implements SubjectObserver {

    private int state;

    public ArrayList<Observer> observers = new ArrayList<Observer>();

    public ViewState(SceneController scene) {
        state = 0;
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

    public static void changeScene(int state){}
}
