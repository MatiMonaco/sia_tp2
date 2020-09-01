package ar.edu.itba.selections;

import ar.edu.itba.TriFunction;
import ar.edu.itba.classes.Character;

import java.util.*;

import java.util.stream.Collectors;

public  abstract class Selection {

    protected int selectionSize;

    public Selection(int selectionSize) {
        this.selectionSize = selectionSize;
    }

    public abstract List<Character> select( List<Character> population,int generation);



    protected  List<Character> customRoulette(int selectionSize, List<Character> population, List<Double> fitness){

        double totalFitness = fitness.stream().mapToDouble(value -> value).sum();
        List<Double> relativeFitness =  fitness.stream().map(value -> value/totalFitness).collect(Collectors.toList());
        List<Double> accumulatedFitness = new ArrayList<>();
        for(int i = 0; i < population.size();i++){
            double acum = 0;
            for(int j = 0; j <= i;j++){
                acum+= relativeFitness.get(j);
            }
            accumulatedFitness.add(acum);
        }
        int k = 0;
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            double random = Math.random();
            for(int i = 0; i < accumulatedFitness.size()-1;i++){
                double acum1 = accumulatedFitness.get(i);
                double acum2 =accumulatedFitness.get(i+1);
                if(acum1 < random && random <= acum2){
                    selection.add(population.get(i+1));
                }
            }
        }
        return  selection;


    }





}
