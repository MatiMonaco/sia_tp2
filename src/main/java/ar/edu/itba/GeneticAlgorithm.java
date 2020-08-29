package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public abstract class GeneticAlgorithm {


    protected List<Character> population;
    protected CharacterType characterType;
    protected BiFunction<List<Character>,List<Character>,Boolean> checkConverge;
    protected BiFunction<Integer,List<Character>,List<Character>> selection;
    protected BiFunction<Character,Character,List<Character>> crossing;
    protected BiFunction<Character,Double,Void> mutate;
    protected BiFunction<Integer,List<Character>,List<Character>> replacement;
    protected double generation;
    protected  FitnessChartMatrix fcm;
    protected List<Double> dataMaxFitness;
    protected List<Double> dataMinFitness;
    protected List<Double> dataAvgFitness;
    protected List<Double> dataGeneticDiv;
    protected List<Double> generationList;


    public GeneticAlgorithm(int initialSize,int selectionsSize,int newGenerationSize,double pm, CharacterType characterType){
        this.characterType = characterType;
        generation = 0;
        loadInitialPopulation(initialSize,characterType);
        initializeCharts();

        start(pm,selectionsSize,newGenerationSize);
    }

    private void initializeCharts(){
        fcm = new FitnessChartMatrix();
        fcm.addChart("minFitness","Generation",0,"Minimum Fitness",0);
        fcm.addChart("maxFitness","Generation",0,"Maximum Fitness",0);
        fcm.addChart("avgFitness","Generation",0,"Average Fitness",0);
        fcm.addChart("divGen","Generation",0,"Genetic Divergence",0);
        dataAvgFitness = new ArrayList<>();
        dataGeneticDiv = new ArrayList<>();
        dataMaxFitness = new ArrayList<>();
        dataMinFitness = new ArrayList<>();
        generationList = new ArrayList<>();
        generationList.add(generation);
        fcm.displayChartMatrix();
    }

    protected void updateCharts(List<Character> population){
        dataAvgFitness.add(fcm.getAverageFitness(population));
        dataMinFitness.add(fcm.getMinimumFitness(population));
        dataMaxFitness.add(fcm.getMaximumFitness(population));
        //NOT IMPLEMENTED YET
        dataGeneticDiv.add(generation);
        //
        fcm.updateSeries("minFitness",generationList,dataMinFitness);
        fcm.updateSeries("maxFitness",generationList,dataMaxFitness);
        fcm.updateSeries("avgFitness",generationList,dataAvgFitness);
        fcm.updateSeries("divGen",generationList,dataGeneticDiv);
    }

    private void loadInitialPopulation(int initialSize,CharacterType characterType){
        for(int i = 0; i < initialSize;i++){

            String types[] = {"helmet","armor","weapon","gloves","boots"};
            List<Equipment> equipment = new ArrayList<>();
            for(int j = 0; j < types.length;j++){
                Equipment eq = Database.getRandomEquipment(types[j]);
                equipment.add(eq);
            }
            Character character = new Character(characterType,new Height(),equipment);
            population.add(character);
        }
    }
    public abstract void start(double pm, int selectionQuantity,int newGenerationSize);
}
