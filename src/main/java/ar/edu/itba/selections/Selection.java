package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.*;

import java.util.stream.Collectors;

public  abstract class Selection {


    public abstract List<Character> select(int selectionSize, List<Character> population,int generation);



    protected  List<Character> customRoulette(int selectionSize, List<Character> population, List<Double> fitness){
        System.out.println("inicio custom roulette");
        double totalFitness = fitness.stream().mapToDouble(value -> value).sum();
        System.out.println("totalfitness: "+totalFitness);
        List<Double> relativeFitness =  fitness.stream().map(value -> value/totalFitness).collect(Collectors.toList());
        System.out.println("relativefitness: "+relativeFitness);
        List<Double> accumulatedFitness = new ArrayList<>();
        for(int i = 0; i < population.size();i++){
            double acum = 0;
            for(int j = 0; j <= i;j++){
                acum+= relativeFitness.get(j);
            }
            accumulatedFitness.add(acum);
        }
        System.out.println("acumfitness: "+accumulatedFitness.size());
        int k = 0;
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            double random = Math.random();
            for(int i = 0; i < accumulatedFitness.size()-1;i++){
                double acum1 = accumulatedFitness.get(i);
                double acum2 =accumulatedFitness.get(i+1);

                if(acum1 < random && random <= acum2){
                    selection.add(population.get(i+1));
                    System.out.println("lo agrego");
                    k++;
                }
            }

        }
        System.out.println("return custom roullete");
        return  selection;


    }





}
