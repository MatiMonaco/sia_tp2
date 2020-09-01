package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Boltzmann extends Selection{

    private double temperature;
    private  double initialTemp;
    private double finalTemp;

    public Boltzmann(double initialTemp, double finalTemp) {

        this.temperature = initialTemp;
        this.initialTemp = initialTemp;
        this.finalTemp = finalTemp;
    }

    @Override
    public List<Character> select(int selectionSize, List<Character> population,int generation) {
        System.out.println("inicio boltzman");
        if(selectionSize == 0){
            return null;
        }
        List<Character> sortedPop = population.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Double> expValList =  population.stream().map(this::expVal).collect(Collectors.toList());
        double avgExpList = expValList.stream().mapToDouble(v->v).average().getAsDouble();
        List<Double> fitness = new ArrayList<>();
        int n = population.size();
        for(int i = 0; i< population.size();i++){
            fitness.add(expValList.get(i)/avgExpList);
        }

        System.out.println("fitness:"+fitness);
        decreaseTemperature(generation+1);
        System.out.println("inicio boltzman");
        return customRoulette(selectionSize,sortedPop,fitness);
    }

    private double expVal(Character character){
        double fitness = character.getFitness();
        return Math.exp(fitness/temperature);
    }

    private void decreaseTemperature(int generation){
        this.temperature = finalTemp + (initialTemp-finalTemp)*Math.exp(-generation);
    }
}
