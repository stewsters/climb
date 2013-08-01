package com.stewsters.climb.creature

import com.stewsters.climb.creature.ai.CreatureAi
import com.stewsters.climb.tile.World
import com.stewsters.util.Point3i
import squidpony.squidcolor.SColor


public class Creature {
    private World world;

    public int x;
    public int y;
    public int z;

    public CreatureAi ai;

    public char glyph;
    public SColor color;
    public boolean climb_gripper = true
    public boolean climb_stemmer = true
    public boolean climb_vaulter = true
    public boolean isDigger = true

    public Creature(params) {
        world = params.world;
        glyph = params.glyph;
        color = params.color;
    }

    public void moveBy(int mx, int my, int mz) {
        Creature other = world.creature(x + mx, y + my, z + mz)
        if (other) {
            attack(other);
        } else {
            if (ai)
                ai.onEnter(x + mx, y + my, z + mz, world.tile(x + mx, y + my, z + mz));
        }
    }

    public void dig(int wx, int wy, int mz) {
        world.dig(wx, wy, mz);
    }

    public void attack(Creature other) {
        world.remove(other);
    }

    public void update() {
        ai.onUpdate();
        if (fall())
            moveBy(0, 0, -1)
    }

    public boolean canEnter(int x, int y, int z) {
        return world.tile(x, y, z).isBlocked
    }

    public boolean fall() {
        if (world.tile(x, y, z - 1).isBlocked)
            return false
        if (climb_gripper) {
            for (Point3i point : new Point3i(x, y, z).vonNeumannNeighborhood()) {
                if (world.tile(point.x, point.y, point.z).isGrippable) {
                    return false
                }
            }
            //stemming between two smooth walls
            if (climb_stemmer) {
                if ((world.tile(x + 1, y, z).isBlocked && world.tile(x - 1, y, z).isBlocked) ||
                        (world.tile(x, y + 1, z).isBlocked && world.tile(x, y - 1, z).isBlocked)) {
                    return false
                }
            }
        }
        return true
    }

    @Override
    public String toString() {
        return "$glyph : $x $y $z"
    }
}
