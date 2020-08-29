package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;

public class FillAll extends GeneticAlgorithm {
    public FillAll(int initialSize, int selectionsSize, int newGenerationSize, double pm, CharacterType characterType) {
        super(initialSize, selectionsSize, newGenerationSize, pm, characterType);

    }

    @Override
    public void start(double pm, int selectionsSize, int newGenerationSize) {

        List<Character> lastPopulation = new ArrayList<>(population);
        while(checkConverge.apply(lastPopulation,population)){
            List<Character> selections = selection.apply(selectionsSize,population);

            List<Character> newGeneration = new ArrayList<>();
            for(int i = 0; i < selectionsSize;i+=2){
                int j = i+1;
                if(i == selectionsSize-1){
                    j = i;
                }
                List<Character> children = crossing.apply(selections.get(i),selections.get(j));
                children.forEach(child->mutate.apply(child,pm));
                newGeneration.addAll(children);
            }

            List<Character> totalPopulation = new ArrayList<>();
            totalPopulation.addAll(population);
            totalPopulation.addAll(newGeneration);
            lastPopulation = new ArrayList<>(population);
            population = replacement.apply(newGenerationSize,totalPopulation);

            generationList.add(++generation);
            updateCharts(population);

        }
    }
}
