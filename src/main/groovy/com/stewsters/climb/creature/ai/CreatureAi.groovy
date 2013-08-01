package com.stewsters.climb.creature.ai

import com.stewsters.climb.creature.Creature
import com.stewsters.climb.tile.TileType

class CreatureAi {
    Creature creature;

    public CreatureAi(Creature creature) {
        this.creature = creature;
        this.creature.ai = this;
    }

    public void onEnter(int x, int y, int z, TileType tile) {
        if (!tile.isBlocked) {
            creature.x = x;
            creature.y = y;
            creature.z = z;
            println(creature)
        } else if (tile.isDiggable && creature.isDigger) {
            creature.dig(x, y, z);
        }

    }

    public void onUpdate() {
    }
}
