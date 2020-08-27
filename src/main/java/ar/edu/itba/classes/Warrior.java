package ar.edu.itba.classes;

import ar.edu.itba.Equipment;

import java.util.List;

public class Warrior extends Character {
    public Warrior(int height, List<Equipment> equipmentList) {
        super(height, equipmentList);
    }

    @Override
    public double getPerformance() {
        return 0.6*getAttack() + 0.6*getDefense();
    }
}
