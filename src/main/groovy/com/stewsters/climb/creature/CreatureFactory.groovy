package com.stewsters.climb.creature

import com.stewsters.climb.creature.ai.FungusAi
import com.stewsters.climb.creature.ai.PlayerAi
import com.stewsters.climb.tile.World
import squidpony.squidcolor.SColor


public class CreatureFactory {
    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer() {
        Creature player = new Creature(world: world, glyph: '@', color: SColor.WHITE);
        world.addAtEmptyLocation(player);
        new PlayerAi(player);
        return player;
    }

    public Creature newFungus() {
        Creature fungus = new Creature(world: world, glyph: 'f', color: SColor.GREEN);
        world.addAtEmptyLocation(fungus);
        new FungusAi(fungus);
        return fungus;
    }


}
