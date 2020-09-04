package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;

public class StructureConvergence extends Convergence{

    private double populationPercentage;
    private int generationLimit;
    private List<Integer> geneticDiversityList;
    private double error;

    public StructureConvergence(double populationPercentage, int generationLimit,double error) {
        this.populationPercentage = populationPercentage;
        this.generationLimit = generationLimit;
        this.error = error;
        this.geneticDiversityList = new ArrayList<>();
    }

    @Override
    public boolean checkConvergence(List<Character> population, int generation) {
        int gd1 =  Character.getGeneticDiversity(population,error);
        if(geneticDiversityList.isEmpty()){
            geneticDiversityList.add(gd1);
        }else {
            int gd2= geneticDiversityList.get(0);
            if(gd1 == gd2){
                geneticDiversityList.add(gd2);
            }else{
                geneticDiversityList = new ArrayList<>();
            }
        }

        if(geneticDiversityList.size()==generationLimit){
            return true;
        }
        return false;

    }
}
