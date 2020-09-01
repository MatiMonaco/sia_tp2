package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ranking extends Selection {
    public Ranking(int selectionSize) {
        super(selectionSize);
    }

    @Override
    public List<Character> select(List<Character> population,int generation) {
        List<Character> sortedPop = population.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Double> fitness = new ArrayList<>();
        int n = population.size();
        for(int i = 1; i<= population.size();i++){
            fitness.add((double)((n-i)/n));
        }
        return customRoulette(selectionSize,sortedPop,fitness);
    }
}
