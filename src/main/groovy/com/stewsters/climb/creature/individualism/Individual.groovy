package com.stewsters.climb.creature.individualism

import com.stewsters.util.NameGen

class Individual {
    public long id
    public Gender gender
    public int age = 0

    public String firstName
    public String lastName

    public Individual father
    public Individual mother

    public float greatness = 0f

    public Individual produceChild(Individual other) {


        def child = new Individual()
        age = 0
        child.gender = Gender.random()

        if (gender == Gender.MALE) {
            child.father = this
            child.mother = other
        } else {
            child.father = other
            child.mother = this
        }

        name(child)
        return child

    }


    public static void name(Individual individual) {

        String name
        if (individual.gender == Gender.MALE) {
            individual.firstName = NameGen.randomMaleFirstName()
        } else {
            individual.firstName = NameGen.randomFemaleFirstName()
        }

        if (individual.father) {
            individual.lastName = individual.father.lastName
        } else if (individual.mother) {
            individual.lastName = individual.mother.lastName
        } else {
            individual.lastName = NameGen.randomLastName()
        }
    }

    public String toString() {
        return "$gender $firstName $lastName"
    }

}
