package ar.edu.itba.classes;

import ar.edu.itba.Equipment;
import ar.edu.itba.Genome;
import ar.edu.itba.Height;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Character implements Comparable<Character> {


   private final CharacterType type;
   private final List<Genome> genomes;
   private Double fitness;

    public Character(CharacterType type,Height height,  List<Equipment> equipmentList) {
        this.type = type;
        this.genomes = new ArrayList<>();
        genomes.add(height);
        genomes.addAll(equipmentList);
    }

    public Character(CharacterType type, List<Genome> genomes){
        this.type = type;
        this.genomes = genomes;
    }

    public static double getMaximumFitness(List<Character> population){
        return population.stream().max(Comparator.comparing(Character::getFitness)).get().getFitness();
    }
    public static double getAverageFitness(List<Character> population){
        return population.stream().mapToDouble(Character::getFitness).sum()/population.size();
    }
    public static double getMinimumFitness(List<Character> population){
        return population.stream().min(Comparator.comparing(Character::getFitness)).get().getFitness();
    }

    public static int getGeneticDiversity(List<Character> population,double error){

        List<Height> diffHeights = new ArrayList<>();
        List<Equipment> diffEquipment = new ArrayList<>();
        int popSize = population.size();
        for(int i = 0; i <popSize;i++){
            Character c = population.get(i);
            List<Genome> genomes = c.genomes;
            if(diffHeights.isEmpty()){
                diffHeights.add((Height) genomes.get(0));
            }else{
                Height h = (Height)genomes.get(0);
                boolean equals = false;
                for(Height height:diffHeights){
                    if(height.equalsWithError(h,error)){
                        equals = true;
                        System.out.println(h+" es igual a "+height );
                        break;
                    }
                }
                if(!equals){
                    diffHeights.add(h);
                }
            }
            for(int j = 1; j < genomes.size();j++){
                System.out.println("genomes:"+genomes);
                Equipment eq = (Equipment) genomes.get(j);
                if(!diffEquipment.isEmpty()){
                    boolean equals = false;
                    for(Equipment equipment:diffEquipment){
                        if(equipment.equalsWithError(eq,error)){
                            equals =true;
                            System.out.println(eq+" es igual a "+equipment );
                            break;
                        }
                    }
                    if(!equals){
                        diffEquipment.add(eq);
                    }
                }else{
                    diffEquipment.add(eq);
                }
            }
        }
        return diffEquipment.size() + diffHeights.size();
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Height height = (Height) ((Character) o).genomes.get(0);

        if(height.equals(genomes.get(0))){
            for(int i = 1; i < genomes.size();i++){
                Equipment eq = (Equipment) ((Character) o).genomes.get(i);
                if(!eq.equals(genomes.get(i))){
                    return false;
                }
            }
        }else{
            return false;
        }

        return true;

    }

    public List<Genome> getGenomes() {
        return genomes;
    }

    public CharacterType getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Character{" +
                " fitness=" + getFitness() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, genomes);
    }

    public double getFitness(){
        fitness = type.getBaseAttackM() * getAttack() + type.getBaseDefenseM() * getDefense();
        return fitness;
    }

    public void mutateGen(int gen){
        genomes.get(gen).mutate();
    }

    public  double getRelativeFitness(double totalFitness){
        return getFitness()/totalFitness;
    }

    private double getStrength(){
        if(genomes == null){
            throw new NullPointerException();
        }
        double sum = 0;
        for(int i = 1;i < genomes.size();i++){
            sum += ((Equipment) genomes.get(i)).getStrength();
        }
        return 100*Math.tanh(0.01*sum);
    }

    private double getAgility(){
        if(genomes == null){
            throw new NullPointerException();
        }
        double sum = 0;
        for(int i = 1;i < genomes.size();i++){
            sum += ((Equipment) genomes.get(i)).getAgility();
        }
        return Math.tanh(0.01*sum);
    }

    private double getProficiency(){
        if(genomes == null){
            throw new NullPointerException();
        }
        double sum = 0;
        for(int i = 1;i < genomes.size();i++){
            sum += ((Equipment) genomes.get(i)).getProficiency();
        }
        return 0.6*Math.tanh(0.01*sum);
    }

    private double getResistance(){
        if(genomes == null){
            throw new NullPointerException();
        }
        double sum = 0;
        for(int i = 1;i < genomes.size();i++){
            sum += ((Equipment) genomes.get(i)).getResistance();
        }
        return Math.tanh(0.01*sum);
    }

    private double getHealth(){
        if(genomes == null){
            throw new NullPointerException();
        }
        double sum = 0;
        for(int i = 1;i < genomes.size();i++){
            sum += ((Equipment) genomes.get(i)).getHealth();
        }
        return 100*Math.tanh(0.01*sum);
    }

    private double getAttackModifier()
    {
        double height = ((Height)genomes.get(0)).getHeight();
        return 0.7-Math.pow(3*height-5,4) + Math.pow(3*height-5,2) + height/4;
    }

    private double getDefenseModifier(){
        double height = ((Height)genomes.get(0)).getHeight();
        return 1.9+Math.pow(2.5*height-4.16,4) - Math.pow(2.5*height-4.16,2) + (3*height)/10;
    }

    protected double getAttack(){

        double agility = getAgility();
        double prof = getProficiency();
        double str = getStrength();
        double am = getAttackModifier();
        return ((agility+prof)*str)*am;
    }

    protected double getDefense(){
        return (getResistance()+getProficiency())*getHealth()*getDefenseModifier();
    }

    @Override
    public int compareTo(Character o) {
        return Double.compare(getFitness(),o.getFitness());
    }
}
