package ar.edu.itba;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Equipment extends Genome{

    protected String name;
    protected String type;
    protected double strength,agility,proficiency,resistance,health;

    public Equipment(String type,String name, double strength, double agility, double proficiency, double resistance, double health) {
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
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", agility=" + agility +
                ", proficiency=" + proficiency +
                ", resistance=" + resistance +
                ", health=" + health +
                '}';
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


    @Override
    public void mutate() {
        int randomInt = (int)(1000000 * Math.random());
        Equipment mutation = Database.getEquipment(type,String.valueOf(randomInt));
        this.strength = mutation.getStrength();
        this.agility = mutation.getAgility();
        this.health = mutation.getHealth();
        this.proficiency = mutation.getProficiency();
        this.resistance = mutation.getResistance();
    }
}
