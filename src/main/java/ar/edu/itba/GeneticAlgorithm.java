package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;
import ar.edu.itba.convergences.Convergence;
import ar.edu.itba.selections.Selection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public abstract class GeneticAlgorithm {


    protected List<Character> population;
    protected CharacterType characterType;
    protected Convergence convergence;
    protected Selection selectionA,selectionB;
    protected BiFunction<Character,Character,List<Character>> crossing;
    protected BiFunction<Character,Double,Void> mutate;
    protected Selection replacementA,replacementB;
    protected FitnessChartMatrix fcm;
    protected int generation;
    private List<Double> dataMaxFitness;
    private List<Double> dataMinFitness;
    private List<Double> dataAvgFitness;
    private List<Double> dataGeneticDiv;
    protected List<Double> generationList;
    protected int selectionsSize;
    protected int newGenerationSize;
    protected int initialSize;
    protected double pm,pa,pb;
    private JFrame frame;
    private double error;

    public GeneticAlgorithm(int initialSize,int selectionsSize,Selection selectionA,Selection selectionB,
                            Selection replacementA,Selection replacementB,int newGenerationSize,double pm,double pa,double pb,
                            Convergence convergence,BiFunction<Character,Character,List<Character>> crossing,BiFunction<Character,Double,Void> mutate, CharacterType characterType,double error){
        this.characterType = characterType;
        this.selectionsSize = selectionsSize;
        this.selectionA = selectionA;
        this.selectionB  =selectionB;
        this.replacementA = replacementA;
        this.replacementB = replacementB;
        this.newGenerationSize = newGenerationSize;
        this.initialSize = initialSize;
        this.convergence = convergence;
        this.crossing = crossing;
        this.mutate  =mutate;
        this.pm = pm;
        this.pa = pa;
        this.pb = pb;
        this.error = error;
        generation = 0;
        loadInitialPopulation(initialSize,characterType);
        initializeCharts();


    }

    private void initializeCharts(){
        fcm = new FitnessChartMatrix();
        fcm.addChart("minFitness","Generation",0,"Minimum Fitness",0, Color.BLUE);
        fcm.addChart("maxFitness","Generation",0,"Maximum Fitness",0,Color.RED);
        fcm.addChart("avgFitness","Generation",0,"Average Fitness",0,Color.magenta);
        fcm.addChart("divGen","Generation",0,"Genetic Divergence",0,Color.green);
        dataAvgFitness = new ArrayList<>();
        dataGeneticDiv = new ArrayList<>();
        dataMaxFitness = new ArrayList<>();
        dataMinFitness = new ArrayList<>();
        generationList = new ArrayList<>();
        this.frame =fcm.displayChartMatrix();


    }

    protected void updateCharts(List<Character> population){

        dataAvgFitness.add(Character.getAverageFitness(population));
        dataMinFitness.add(Character.getMinimumFitness(population));
        dataMaxFitness.add(Character.getMaximumFitness(population));
        //NOT IMPLEMENTED YET
        dataGeneticDiv.add((double) Character.getGeneticDiversity(population,error));
        //
        fcm.updateSeries("minFitness",generationList,dataMinFitness);
        fcm.updateSeries("maxFitness",generationList,dataMaxFitness);
        fcm.updateSeries("avgFitness",generationList,dataAvgFitness);
        fcm.updateSeries("divGen",generationList,dataGeneticDiv);
        fcm.repaint();
    }

    private void loadInitialPopulation(int initialSize,CharacterType characterType){
        population = new ArrayList<>();
        for(int i = 0; i < initialSize;i++){

            String[] types = {"helmet","armor","weapon","gloves","boots"};
            List<Equipment> equipment = new ArrayList<>();
            for (String type : types) {
                Equipment eq = Database.getRandomEquipment(type);
                equipment.add(eq);
            }
            Character character = new Character(characterType,new Height(),equipment);
            population.add(character);
        }
    }

    public List<Character> getPopulation() {
        return population;
    }

    public abstract void start();
}
