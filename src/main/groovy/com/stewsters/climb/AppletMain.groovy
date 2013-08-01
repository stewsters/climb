package com.stewsters.climb

import com.stewsters.climb.screen.Screen
import com.stewsters.climb.screen.StartScreen
import com.stewsters.util.RenderConfig
import squidpony.squidgrid.gui.swing.SwingPane

import java.applet.Applet
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

public class AppletMain extends Applet implements KeyListener {
//    private static final long serialVersionUID = 2560255315130084198L;

    private SwingPane display;
    private Screen screen;

    public AppletMain() {
        super();
        display = new SwingPane();
        display.initialize(RenderConfig.screenWidth, RenderConfig.screenHeight, new Font("Ariel", Font.BOLD, 12))
        add(display);

        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void init() {
        super.init();
        this.setSize(display.getWidth() + 20, display.getHeight() + 20);
    }

    public void repaint() {
        super.repaint()
        clear(display)
        screen.displayOutput(display);
        display.refresh()
    }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void clear(SwingPane display) {
        for (int x = 0; x < RenderConfig.screenWidth; x++) {
            for (int y = 0; y < RenderConfig.screenHeight; y++) {
                display.clearCell(x, y)
            }
        }
    }
}