package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;

public class ContentConvergence extends Convergence{

    private int generationLimit;
    private List<Double> maximumList;
    private double delta;
    public ContentConvergence(int generationLimit,double delta) {
        this.generationLimit = generationLimit;
        this.maximumList = new ArrayList<>();
        this.delta = delta;
    }

    @Override
    public boolean checkConvergence(List<Character> population, int generation) {
        double m1 =  Character.getMaximumFitness(population).getFitness();
        if(maximumList.isEmpty()){
            maximumList.add(m1);
        }else {
            double m2= maximumList.get(0);
            if(isEquals(m1,m2,delta)){
                maximumList.add(m2);
            }else{
                maximumList = new ArrayList<>();
            }
        }

        if(maximumList.size()==generationLimit){
            return true;
        }
        return false;

    }


    private boolean isEquals(double m1, double m2, double delta){
        if(m2 >= m1-delta && m2 <= m1+delta){
            return true;
        }
        return false;
    }
}
