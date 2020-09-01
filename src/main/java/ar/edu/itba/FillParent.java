package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;

public class FillParent extends GeneticAlgorithm {
    public FillParent(int initialSize, int selectionsSize, int newGenerationSize, double pm, CharacterType characterType) {
        super(initialSize,selectionsSize,newGenerationSize,pm, characterType);
    }

    @Override
    public void start() {
        List<Character> lastPopulation = new ArrayList<>(population);
        while(checkConverge.apply(lastPopulation,population)){
            List<Character> selections = selection.select(population,generation);
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
            lastPopulation = new ArrayList<>(population);
            if(selectionsSize > newGenerationSize){
                population = replacement.apply(newGenerationSize,newGeneration);
            }else{
                int diff = newGenerationSize - selectionsSize;
                List<Character> parents = replacement.apply(diff,population);
                newGeneration.addAll(parents);
                population = new ArrayList<>(newGeneration);
                generation++;
            }
        }
    }
}
