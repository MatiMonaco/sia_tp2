package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Elite extends Selection {
    public Elite(int selectionSize) {
        super(selectionSize);
    }

    @Override
    public List<Character> select( List<Character> population,int generation) {
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
}
