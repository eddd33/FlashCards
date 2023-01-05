package com.example.flashcards.controller.studystrategy;

import com.example.flashcards.models.Study;

public interface StudyStrategy {
    public void nextCard(double coef, Study study);

}