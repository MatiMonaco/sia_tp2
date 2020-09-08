package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;
import ar.edu.itba.convergences.Convergence;
import ar.edu.itba.selections.Selection;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
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
    private Character bestCharacter;

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

    protected void showResults(){
        StringBuilder sb = new StringBuilder();
        int lastIndex = generationList.size()-1;

        StringBuilder doubleFormatSb = new StringBuilder("#.");
        while(error < 1){
            error*=10;
           doubleFormatSb.append("#");
        }
        DecimalFormat df2 = new DecimalFormat(doubleFormatSb.toString());
        sb.append("Generation: "+generationList.get(lastIndex)+'\n');
        sb.append("Last Maximum Fitness: "+df2.format(dataMaxFitness.get(lastIndex))+'\n');
        sb.append("Best Fitness: "+df2.format(bestCharacter.getFitness())+'\n');
        System.out.println("best character "+bestCharacter);
        Height height = (Height) bestCharacter.getGenomes().get(0);
        sb.append("Height: "+df2.format(height.getHeight())+'\n');
        Equipment aux = (Equipment) bestCharacter.getGenomes().get(1);
        String s1 = aux.getType().substring(0, 1).toUpperCase();
        String type = s1 + aux.getType().substring(1);
        sb.append(type+": "+aux.getName()+'\n');
        aux = (Equipment) bestCharacter.getGenomes().get(2);
        s1 = aux.getType().substring(0, 1).toUpperCase();
        type = s1 + aux.getType().substring(1);
        sb.append(type+": "+aux.getName()+'\n');
        aux = (Equipment) bestCharacter.getGenomes().get(3);
        s1 = aux.getType().substring(0, 1).toUpperCase();
        type = s1 + aux.getType().substring(1);
        sb.append(type+": "+aux.getName()+'\n');
        aux = (Equipment) bestCharacter.getGenomes().get(4);
        s1 = aux.getType().substring(0, 1).toUpperCase();
        type = s1 + aux.getType().substring(1);
        sb.append(type+": "+aux.getName()+'\n');
        aux = (Equipment) bestCharacter.getGenomes().get(5);
        s1 = aux.getType().substring(0, 1).toUpperCase();
        type = s1 + aux.getType().substring(1);
        sb.append(type+": "+aux.getName()+'\n');




        JOptionPane.showMessageDialog(frame,sb.toString(),"Results",JOptionPane.INFORMATION_MESSAGE);
    }

    private void initializeCharts(){
        fcm = new FitnessChartMatrix();
        fcm.addChart("minFitness","Generation",0,"Minimum Fitness",0, Color.BLUE);
        fcm.addChart("maxFitness","Generation",0,"Maximum Fitness",0,Color.RED);
        fcm.addChart("avgFitness","Generation",0,"Average Fitness",0,Color.magenta);
        fcm.addChart("divGen","Generation",0,"Genetic Diversity",0,Color.green);
        dataAvgFitness = new ArrayList<>();
        dataGeneticDiv = new ArrayList<>();
        dataMaxFitness = new ArrayList<>();
        dataMinFitness = new ArrayList<>();
        generationList = new ArrayList<>();
        this.frame =fcm.displayChartMatrix();


    }


    protected void updateCharts(List<Character> population){
        Character maximum =Character.getMaximumFitness(population);
        System.out.println(generationList.get(generationList.size()-1));
        System.out.println(maximum);
        bestCharacter = bestCharacter == null ? maximum: bestCharacter.getFitness() >= maximum.getFitness() ? bestCharacter: maximum;
        dataAvgFitness.add(Character.getAverageFitness(population));
        dataMinFitness.add(Character.getMinimumFitness(population));
        dataMaxFitness.add(Character.getMaximumFitness(population).getFitness());
        dataGeneticDiv.add((double) Character.getGeneticDiversity(population,error));

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
