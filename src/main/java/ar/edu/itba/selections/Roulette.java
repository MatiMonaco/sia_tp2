package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Roulette extends Selection {

    @Override
    public List<Character> select(int selectionSize,List<Character> population,int generation) {
        if(selectionSize == 0){
            return null;
        }
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
}
