package ar.edu.itba.classes;

import ar.edu.itba.Equipment;

import java.util.List;

public class Archer extends Character {
    public Archer(int height, List<Equipment> equipmentList) {
        super(height, equipmentList);
    }

    @Override
    public double getFitness() {
        return 0.9 * getAttack() + 0.1*getDefense();
    }
}
