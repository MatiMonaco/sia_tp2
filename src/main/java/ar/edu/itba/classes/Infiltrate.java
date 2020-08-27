package ar.edu.itba.classes;

import ar.edu.itba.Equipment;

import java.util.List;

public class Infiltrate extends Character {
    public Infiltrate(int height, List<Equipment> equipmentList) {
        super(height, equipmentList);
    }

    @Override
    public double getPerformance() {
        return 0.8*getAttack() + 0.3*getDefense();
    }
}
