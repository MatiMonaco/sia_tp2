package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;

public class StructureConvergence extends Convergence{

    private double populationPercentage;
    private int generationLimit;
    private int generationCounter;
    private double error;


    public StructureConvergence(double populationPercentage, int generationLimit,double error) {
        this.populationPercentage = populationPercentage;
        this.generationLimit = generationLimit;
        this.error = error;
        this.generationCounter = 0;

    }

    @Override
    public boolean checkConvergence(List<Character> population, int generation) {
        int gd =  Character.getGeneticDiversity(population,error);

        int totalGenomes = population.size() * 6;
        int sameGenetic = totalGenomes - gd;
        double percentage = (double)sameGenetic/totalGenomes;
        if (percentage >= populationPercentage) {

                generationCounter++;
            }else{
                generationCounter=0;
            }

        if  (generationCounter>=generationLimit){
            return true;
        }
        return false;

    }
}
