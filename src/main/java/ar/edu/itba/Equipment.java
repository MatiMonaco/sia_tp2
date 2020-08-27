package ar.edu.itba;

import java.util.Objects;

public abstract class Equipment {

    protected String name;
    protected int strength,agility,proficiency,resistance,health;

    public Equipment(String name, int strength, int agility, int proficiency, int resistance, int health) {
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
        return strength == equipment.strength &&
                agility == equipment.agility &&
                proficiency == equipment.proficiency &&
                resistance == equipment.resistance &&
                health == equipment.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, agility, proficiency, resistance, health);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
