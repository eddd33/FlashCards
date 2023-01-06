package com.example.flashcards.view;

import com.example.flashcards.controller.SceneController;

import java.util.ArrayList;

public class ViewState implements SubjectObserver {

    private int state;
    private SceneController controller;
    public ArrayList<Observer> observers = new ArrayList<>();


    public ViewState(SceneController controller) {
        state = 0;
        this.controller = controller;
    }



    /*
     * SubjectObserver :
     * The following methods are used implementing the SubjectObserver interface.
     */
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



    /**
     * Method used to get the integer that define in which state we are in.
     *
     * @return state
     * type : int
     */
    public int getState() {
        return state;
    }



    /**
     * Method used to change the integer that define in which state we are in.
     *
     * @param state
     * The index of the scene we want to switch to.
     * The following index are for :
     *      0 -> SelectionView
     *      1 -> LearningView
     *      2 -> CreateView
     *      3 -> DeckEditView
     *      4 -> StatView
     */
    public void changeScene(int state){
        controller.changeScene(state);
        this.state=state;
    }


    /**
     * Method is used to change the names
     *
     * @param name
     * the new name
     */
    public void changeName(String name) {
        controller.changename(name);
    }
}
