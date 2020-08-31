package ar.edu.itba;

import ar.edu.itba.classes.Character;

import java.util.*;

import java.util.stream.Collectors;

public class Selection {

    public static final Map<String, TriFunction<Integer, List<Character>,List<Character>,Double>> map = new HashMap<>();

    public static List<Character> elite(int selectionSize,List<Character> population,double parameter){

        if(population.isEmpty()){
           return null;
        }

       List<Character> sortedList = population.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

       int k = 0;
       int populationSize = sortedList.size();
       List<Character> selection = new ArrayList<>();
       while(k < selectionSize){
           int i = k%populationSize;
           selection.add(sortedList.get(i));
           k++;
       }
       return selection;
    }

    private static List<Character> customRoulette(int selectionSize,List<Character> population,List<Double> fitness){

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

    public static List<Character> roulette(int selectionSize,List<Character>population,double parameter){
        double totalFitness = population.stream().mapToDouble(Character::getFitness).sum();
        List<Double> relativeFitness =  population.stream().map(value -> value.getRelativeFitness(totalFitness)).collect(Collectors.toList());
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

    public static List<Character> universal(int selectionSize,List<Character>population,double parameter){
        double totalFitness = population.stream().mapToDouble(Character::getFitness).sum();
        List<Double> relativeFitness =  population.stream().map(value -> value.getRelativeFitness(totalFitness)).collect(Collectors.toList());
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
            double random = (Math.random()+k)/selectionSize;
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


    public static List<Character> ranking(int selectionSize,List<Character>population,double parameter){
        List<Character> sortedPop = population.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Double> fitness = new ArrayList<>();
        int n = population.size();
        for(int i = 1; i<= population.size();i++){
            fitness.add((double)((n-i)/n));
        }
        return customRoulette(selectionSize,sortedPop,fitness);
    }

    public static List<Character> boltzmann(int selectionSize,List<Character>population,double parameter){
       return  null;
    }

    public static List<Character> deterministicTournament(int selectionSize,List<Character>population,double M ){
        int k = 0;
        int size = population.size();
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            int m = 0;
            Character winner = null;
            while(m< M){
                int random = (int) (size*Math.random());
                Character aux = population.get(random);
                if(winner == null) {
                    winner = aux;
                }else if(aux.getFitness() > winner.getFitness()){
                    winner = aux;
                }
                m++;
            }
            selection.add(winner);
            k++;
        }
        return selection;
    }

    public static List<Character> probabilisticTournament(int selectionSize,List<Character>population,double threshold ){
        if(threshold < 0.5 || threshold >1){
            throw new IllegalArgumentException();
        }
        int k = 0;
        int size = population.size();
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            Character winner;

            int random1 = (int) (size*Math.random());
            int random2 = (int) (size*Math.random());
            Character aux1 = population.get(random1);
            Character aux2 = population.get(random2);

            double randomThreshold = (size*Math.random());

            if(randomThreshold < threshold){
                winner = aux1.getFitness() > aux2.getFitness() ? aux1 : aux2;

            }else{
                winner = aux1.getFitness() < aux2.getFitness() ? aux1 : aux2;
            }

            selection.add(winner);
            k++;
        }
        return selection;
    }


}
