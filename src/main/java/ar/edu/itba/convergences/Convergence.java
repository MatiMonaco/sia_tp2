package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.List;

public abstract class Convergence {


    public abstract boolean checkConvergence(List<Character> population, int generation);
}
