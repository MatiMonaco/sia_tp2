package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.List;

public class GenerationQuantityConvergence extends Convergence {


    private int generationLimit;

    public GenerationQuantityConvergence(int generationLimit) {

        this.generationLimit = generationLimit;
    }

    @Override
    public boolean checkConvergence(List<Character> population, int generation) {
        return generation >= generationLimit;
    }
}
