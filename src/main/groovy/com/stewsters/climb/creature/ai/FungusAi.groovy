package com.stewsters.climb.creature.ai

import com.stewsters.climb.creature.Creature
import com.stewsters.climb.creature.CreatureFactory
import com.stewsters.util.MathUtils

public class FungusAi extends CreatureAi {
    private CreatureFactory factory;
    private int spreadcount;

    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory
    }

    @Override
    public void onUpdate() {
        if (spreadcount < 5 && Math.random() < 0.02)
            spread();
    }

    private void spread() {
        int x = creature.x + MathUtils.getIntInRange(-5, 5);
        int y = creature.y + MathUtils.getIntInRange(-5, 5);
        int z = creature.z

        if (!creature.canEnter(x, y, z))
            return;

        Creature child = factory.newFungus();
        child.x = x;
        child.y = y;
        child.z = z;
        spreadcount++;
    }
}

