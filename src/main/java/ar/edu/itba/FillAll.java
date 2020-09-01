package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;
import ar.edu.itba.convergences.Convergence;
import ar.edu.itba.selections.Selection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class FillAll extends GeneticAlgorithm {
    public FillAll(int initialSize, int selectionSize, Selection selectionA, Selection selectionB, Selection replacementA, Selection replacementB,
                   int newGenerationSize, double pm, double pa, double pb, Convergence convergence,
                   BiFunction<Character,Character,List<Character>> crossing, BiFunction<Character,Double,Void> mutate, CharacterType characterType) {
        super(initialSize,selectionSize,selectionA,selectionB,replacementA,replacementB,newGenerationSize,pm,pa,pb,convergence,crossing,mutate,characterType);

    }

    @Override
    public void start() {
        fcm.displayChartMatrix();
        List<Character> lastPopulation = new ArrayList<>(population);
        while(!convergence.checkConvergence(lastPopulation,population,generation)){
            System.out.println("While");
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
            for(int i = 0; i < selectionsSize-1;i+=2){
                int j = i+1;
//                if(i == selectionsSize-1){
//                    j = i;
//                }
                List<Character> children = crossing.apply(selections.get(i),selections.get(j));
                //mutation
                children.forEach(child->mutate.apply(child,pm));
                newGeneration.addAll(children);
            }


            lastPopulation = new ArrayList<>(population);

            //replacement
            List<Character> totalPopulation = new ArrayList<>();
            totalPopulation.addAll(population);
            totalPopulation.addAll(newGeneration);
            int replacementSizeA = (int) (newGenerationSize * pb);
            int replacementSizeB = newGenerationSize-replacementSizeA;
            population = new ArrayList<>();
            List<Character> replaceA = null,replaceB = null;
            if(replacementA!= null){
                replaceA = replacementA.select(replacementSizeA,totalPopulation,generation);
            }

            if(replacementB != null){
               replaceB = replacementB.select(replacementSizeB,totalPopulation,generation);

            }
            if(replaceA != null){
                population.addAll(replaceA);
            }
            if(replaceB !=null){
                population.addAll(replaceB);
            }

            generation++;
            generationList.add(((double)generation));
            System.out.println("generation: "+generation);
            updateCharts(population);

        }
    }
}
