package com.stewsters.climb.creature.individualism

import com.stewsters.util.MathUtils

public enum Gender {
    MALE,
    FEMALE

    public static Gender random() {
        MathUtils.boolean ? Gender.MALE : Gender.FEMALE
    }
}