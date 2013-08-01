package com.stewsters.climb.screen

import squidpony.squidcolor.SColor
import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent;

public class StartScreen implements Screen {

    private int screenWidth;
    private int screenHeight;
    private int selection = 0;

    private def options

    public StartScreen() {
        screenWidth = 80;
        screenHeight = 40;
        options = [
                [title: "Start a New Game", action: { return new PlayScreen() }],
                [title: "Keys", action: { return new InstructionScreen() }],
                [title: "Exit", action: { System.exit(0) }]
        ]
    }

    public void displayOutput(SwingPane display) {
        display.placeHorizontalString(20, 1, "Ninja Elimination");

        options.eachWithIndex { option, i ->
            boolean selected = (i == selection)
            display.placeHorizontalString(20, 2 * i + 10, option.title + (selected ? " [enter]" : ""), selected ? SColor.YELLOW : SColor.WHITE, SColor.BLACK);
        }

    }

    public Screen respondToUserInput(KeyEvent key) {

        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0)
                break;
            case KeyEvent.VK_ENTER:
                return options[selection].action()
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                selection--;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                selection++;
                break;

        }
        return this;
    }


}