package com.example.flashcards;

import java.net.URL;
public class ImageGetter {
    public URL getImage() {
        return getClass().getResource("images/FlashMcCards.jpg");
    }
}
