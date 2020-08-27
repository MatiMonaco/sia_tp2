package ar.edu.itba;

import java.util.Objects;

public class Equipment {

    protected String name;
    protected double strength,agility,proficiency,resistance,health;

    public Equipment(String name, double strength, double agility, double proficiency, double resistance, double health) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.proficiency = proficiency;
        this.resistance = resistance;
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Double.compare(strength, equipment.strength) == 0 &&
                Double.compare(agility, equipment.agility) == 0 &&
                Double.compare(proficiency, equipment.proficiency) == 0 &&
                Double.compare(resistance, equipment.resistance) == 0 &&
                Double.compare(health, equipment.health) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, agility, proficiency, resistance, health);
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }



    public double getProficiency() {
        return proficiency;
    }



    public double getResistance() {
        return resistance;
    }



    public double getHealth() {
        return health;
    }


}
