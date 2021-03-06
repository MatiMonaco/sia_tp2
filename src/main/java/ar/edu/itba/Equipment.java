package ar.edu.itba;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Equipment implements Genome, Cloneable{

    protected String name;
    protected String type;
    protected double strength,agility,proficiency,resistance,health;
    public  enum types {helmet,armor,weapon,gloves,boots}
    public Equipment(String type,String name, double strength, double agility, double proficiency, double resistance, double health) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.proficiency = proficiency;
        this.resistance = resistance;
        this.health = health;
    }

    public boolean equalsWithError(Equipment eq,double error){
        return (Math.abs(strength - eq.strength) <= error) &&
                (Math.abs(agility - eq.agility) <= error) &&
                (Math.abs(proficiency - eq.proficiency) <= error) &&
                (Math.abs(resistance - eq.resistance) <= error) &&
                (Math.abs(health - eq.health) <= error);
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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public void mutate() {
        Equipment mutation = Database.getRandomEquipment(type);
        this.name = mutation.getName();
        this.strength = mutation.getStrength();
        this.agility = mutation.getAgility();
        this.health = mutation.getHealth();
        this.proficiency = mutation.getProficiency();
        this.resistance = mutation.getResistance();
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
