package com.stewsters.climb.screen

import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent

public class WinScreen implements Screen {

    public void displayOutput(SwingPane display) {
        display.placeHorizontalString(10, 1, "You won.");
        display.placeHorizontalString(10, 2, "-- press [enter] to restart --");
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new StartScreen() : this;
    }
}