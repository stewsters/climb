package com.stewsters.climb.screen

import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent


public interface Screen {
    public void displayOutput(SwingPane display);

    public Screen respondToUserInput(KeyEvent key);
}