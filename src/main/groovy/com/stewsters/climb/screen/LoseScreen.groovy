package com.stewsters.climb.screen

import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent

public class LoseScreen implements Screen {

    public void displayOutput(SwingPane display) {
        display.placeHorizontalString(1, 1, "You lost.");
        display.placeHorizontalString(10, 10, "-- press [enter] to restart --");
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new StartScreen() : this;
    }
}