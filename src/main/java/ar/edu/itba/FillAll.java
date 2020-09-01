package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;

public class FillAll extends GeneticAlgorithm {
    public FillAll(int initialSize, int newGenerationSize, double pm, CharacterType characterType) {
        super(initialSize,newGenerationSize,pm,characterType);

    }

    @Override
    public void start() {

        List<Character> lastPopulation = new ArrayList<>(population);
        while(checkConverge.apply(lastPopulation,population)){
            List<Character> selections = selection.select(population,generation);

            List<Character> newGeneration = new ArrayList<>();
            for(int i = 0; i < selectionsSize-1;i+=2){
                int j = i+1;
//                if(i == selectionsSize-1){
//                    j = i;
//                }
                List<Character> children = crossing.apply(selections.get(i),selections.get(j));
                children.forEach(child->mutate.apply(child,pm));
                newGeneration.addAll(children);
            }

            List<Character> totalPopulation = new ArrayList<>();
            totalPopulation.addAll(population);
            totalPopulation.addAll(newGeneration);
            lastPopulation = new ArrayList<>(population);
            population = replacement.apply(newGenerationSize,totalPopulation);
            generation++;
            generationList.add(((double)generation));
            updateCharts(population);

        }
    }
}
