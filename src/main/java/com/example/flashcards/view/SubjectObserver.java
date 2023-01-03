package com.example.flashcards.view;

public interface SubjectObserver {
    void addObserver(Observer o);
    void notifyObserver();
}
