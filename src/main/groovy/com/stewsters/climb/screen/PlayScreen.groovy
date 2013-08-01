package com.stewsters.climb.screen

import com.stewsters.climb.creature.Creature
import com.stewsters.climb.creature.CreatureFactory
import com.stewsters.climb.tile.TileType
import com.stewsters.climb.tile.World
import com.stewsters.climb.worldBuilder.RandomTempleBuilder
import squidpony.squidgrid.gui.swing.SwingPane

import java.awt.event.KeyEvent

public class PlayScreen implements Screen {

    private World world;

    private Creature player
    private int screenWidth;
    private int screenHeight;

    public PlayScreen() {
        screenWidth = 80;
        screenHeight = 40;

        createWorld();

        CreatureFactory creatureFactory = new CreatureFactory(world)
        createCreatures(creatureFactory)

    }

    private void createWorld() {
//        world = new RandomCaveBuilder(100, 100, 30)
//                .makeCaves()
//                .build();

        world = new RandomTempleBuilder(100, 100, 30)
                .horizontalFillBelow(TileType.AIR, 30)
                .horizontalFillBelow(TileType.SAND, 20)
                .horizontalFillBelow(TileType.WALL, 2)
                .generateStructure(3)
                .build()
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        player = creatureFactory.newPlayer()

//        for (int i = 0; i < 100; i++) {
//            creatureFactory.newFungus()
//        }
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x - (screenWidth / 2) as int, world.width - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y - (screenHeight / 2) as int, world.height - screenHeight));
    }

    //TODO: this would be nice if you could see opponent's sight cones.
    // Red is hostile, orange is searching, yellow is relaxed
    private void displayTiles(SwingPane display, int left, int top) {
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                display.placeCharacter(x, y, world.glyph(wx, wy, player.z), world.color(wx, wy, player.z))
            }
        }
    }

    private void displayCreatures(SwingPane display, int left, int top) {
        for (Creature creature : world.creatures) {
            int sx = creature.x - left
            int sy = creature.y - top
            int sz = creature.z
            if (onScreen(sx, sy, sz))
                display.placeCharacter(sx, sy, creature.glyph, creature.color)
        }

    }

    private boolean onScreen(int x, int y, int z) {
        return !(x < 0 || x >= screenWidth || y < 0 || y > screenHeight || z != player.z)
    }

    public void displayOutput(SwingPane display) {

        int left = getScrollX()
        int top = getScrollY()

        displayTiles(display, left, top)
        displayCreatures(display, left, top)
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseScreen();
                break;
            case KeyEvent.VK_ENTER:
                return new WinScreen();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
            case KeyEvent.VK_NUMPAD4:
                player.moveBy(-1, 0, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
            case KeyEvent.VK_NUMPAD6:
                player.moveBy(1, 0, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
            case KeyEvent.VK_NUMPAD8:
                player.moveBy(0, -1, 0);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
            case KeyEvent.VK_NUMPAD2:
                player.moveBy(0, 1, 0);
                break;
            case KeyEvent.VK_Y:
            case KeyEvent.VK_NUMPAD7:
                player.moveBy(-1, -1, 0);
                break;
            case KeyEvent.VK_U:
            case KeyEvent.VK_NUMPAD9:
                player.moveBy(1, -1, 0);
                break;
            case KeyEvent.VK_B:
            case KeyEvent.VK_NUMPAD1:
                player.moveBy(-1, 1, 0);
                break;
            case KeyEvent.VK_N:
            case KeyEvent.VK_NUMPAD3:
                player.moveBy(1, 1, 0);
                break;
            case KeyEvent.VK_PERIOD:
                player.moveBy(0, 0, 1)
                break
            case KeyEvent.VK_COMMA:
                player.moveBy(0, 0, -1)
                break
        }
        world.update()
        return this;
    }
}