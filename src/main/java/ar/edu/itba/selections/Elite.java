package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Elite extends Selection {


    @Override
    public List<Character> select(int selectionSize, List<Character> population,int generation) {
//        System.out.println("inicio elite");
        if(selectionSize == 0){
            return null;
        }
        if(population.isEmpty()){
//            System.out.println("Population is empty");
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
//        System.out.println("return elite");
        return selection;
    }
}
