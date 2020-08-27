package ar.edu.itba.classes;

import ar.edu.itba.Equipment;

import java.util.List;

public abstract class Character {

   protected int height;
   protected List<Equipment> equipmentList;

    public abstract double getFitness();

    public Character(int height, List<Equipment> equipmentList) {
        this.height = height;
        this.equipmentList = equipmentList;
    }

    public  double getRelativeFitness(List<Character> individuals){
        double sum = individuals.stream().mapToDouble(Character::getFitness).sum();
        return getFitness()/sum;
    }

    private double getStrength(){
        if(equipmentList ==null){
            throw new NullPointerException();
        }
        double sum =equipmentList.stream().mapToDouble(Equipment::getStrength).sum();
        return 100*Math.tanh(0.01*sum);
    }
    private double getAgility(){
        if(equipmentList ==null){
            throw new NullPointerException();
        }
        double sum = equipmentList.stream().mapToDouble(Equipment::getAgility).sum();
        return Math.tanh(0.01*sum);
    }
    private double getProficiency(){
        if(equipmentList ==null){
            throw new NullPointerException();
        }
        double sum= equipmentList.stream().mapToDouble(Equipment::getProficiency).sum();
        return 0.6*Math.tanh(0.01*sum);
    }
    private double getResistance(){
        if(equipmentList ==null){
            throw new NullPointerException();
        }
        double sum = equipmentList.stream().mapToDouble(Equipment::getResistance).sum();
        return Math.tanh(0.01*sum);
    }
    private double getHealth(){
        if(equipmentList ==null){
            throw new NullPointerException();
        }
        double sum = equipmentList.stream().mapToDouble(Equipment::getHealth).sum();
        return 100*Math.tanh(0.01*sum);
    }

    private double getAttackModifier(){
        return 0.7-Math.pow(3*height-5,4) + Math.pow(3*height-5,5) + height/4;
    }
    private double getDefenseModifier(){
        return 1.9-Math.pow(2.5*height-4.16,4) + Math.pow(2.5*height-4.16,5) + 3*height/10;
    }

    protected double getAttack(){
        return (getAgility()+getProficiency())*getStrength()*getAttackModifier();
    }

    protected double getDefense(){
        return (getResistance()+getProficiency())*getHealth()*getDefenseModifier();
    }





}
