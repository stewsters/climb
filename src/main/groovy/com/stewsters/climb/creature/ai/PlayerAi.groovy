package com.stewsters.climb.creature.ai

import com.stewsters.climb.creature.Creature

public class PlayerAi extends CreatureAi {

    public PlayerAi(Creature creature) {
        super(creature);
    }

    @Override
    public void onUpdate() {
        //plummet if not holding or standing on something
    }
}