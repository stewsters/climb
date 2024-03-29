package com.stewsters.climb

import com.stewsters.climb.screen.Screen
import com.stewsters.climb.screen.StartScreen
import com.stewsters.util.RenderConfig
import squidpony.squidgrid.gui.swing.SwingPane

import javax.swing.*
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

public class ApplicationMain extends JFrame implements KeyListener {
//    private static final long serialVersionUID = 1060623638149583738L;

    private SwingPane display;
    private Screen screen;

    public ApplicationMain() {
        super();
        display = new SwingPane();
        display.initialize(RenderConfig.screenWidth, RenderConfig.screenHeight, new Font("Ariel", Font.BOLD, 12))
        add(display);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint() {

        clear(display)
        screen.displayOutput(display);
        display.refresh()
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }


    public void clear(SwingPane display) {
        for (int x = 0; x < RenderConfig.screenWidth; x++) {
            for (int y = 0; y < RenderConfig.screenHeight; y++) {
                display.clearCell(x, y)
            }
        }
    }
}