package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;

public class FillParent extends GeneticAlgorithm {
    public FillParent(int initialSize,int selectionSize, int newGenerationSize, double pm,double pa,double pb, CharacterType characterType) {
        super(initialSize,selectionSize,newGenerationSize,pm,pa,pb ,characterType);
    }

    @Override
    public void start() {
        List<Character> lastPopulation = new ArrayList<>(population);
        while(checkConverge.apply(lastPopulation,population)){

            //selection
            int selectionSizeA = (int) (selectionsSize * pa);
            int selectionSizeB = selectionsSize-selectionSizeA;
            List<Character> selectA = selectionA.select(selectionSizeA,population,generation);
            List<Character> selectB = selectionB.select(selectionSizeB,population,generation);
            List<Character> selections = new ArrayList<>();
            if(selectA != null){
                selections.addAll(selectA);
            }
            if(selectB != null){
                selections.addAll(selectB);
            }

            //crossing
            List<Character> newGeneration = new ArrayList<>();

            for(int i = 0; i < selectionsSize;i+=2){
                int j = i+1;
                if(i == selectionsSize-1){
                    j = i;
                }
                List<Character> children = crossing.apply(selections.get(i),selections.get(j));
                //mutation
                children.forEach(child->mutate.apply(child,pm));
                newGeneration.addAll(children);
            }
            lastPopulation = new ArrayList<>(population);

            //replacement
            if(selectionsSize > newGenerationSize){
                int replacementSizeA = (int) (newGenerationSize * pb);
                int replacementSizeB = newGenerationSize-replacementSizeA;

                List<Character> replacementA = selectionA.select(replacementSizeA,newGeneration,generation);
                List<Character> replacementB = selectionB.select(replacementSizeB,newGeneration,generation);
                population = new ArrayList<>();
                if(replacementA != null){
                    population.addAll(replacementA);
                }
                if(replacementB !=null){
                    population.addAll(replacementB);
                }
            }else{
                int diff = newGenerationSize - selectionsSize;
                int replacementSizeA = (int) (diff * pb);
                int replacementSizeB = diff-replacementSizeA;

                List<Character> replacementA = selectionA.select(replacementSizeA,population,generation);
                List<Character> replacementB = selectionB.select(replacementSizeB,population,generation);
                population = new ArrayList<>();
                if(replacementA != null){
                    population.addAll(replacementA);
                }
                if(replacementB !=null){
                    population.addAll(replacementB);
                }
                population.addAll(newGeneration);
                generation++;
            }
        }
    }
}
