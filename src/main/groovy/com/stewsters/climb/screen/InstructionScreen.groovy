package com.stewsters.climb.screen

import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent


public class InstructionScreen implements Screen {

    public void displayOutput(SwingPane display) {
        display.placeHorizontalString(10, 1, "Instructions on how to play the game");
        display.placeHorizontalString(10, 10, "-- press [enter] to go back to Main Menu-");
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new StartScreen() : this;
    }

}
