package ar.edu.itba.classes;

import ar.edu.itba.Equipment;

import java.util.List;

public class Tank extends Character {
    public Tank(int height, List<Equipment> equipmentList) {
        super(height, equipmentList);
    }

    @Override
    public double getFitness() {
        return 0.3*getAttack() + 0.8*getDefense();
    }
}
