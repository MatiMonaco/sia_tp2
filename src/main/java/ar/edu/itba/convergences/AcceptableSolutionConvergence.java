package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.List;

public class AcceptableSolutionConvergence extends Convergence {
        private double acceptableSolution;
        private double delta;
        public AcceptableSolutionConvergence(double acceptableSolution,double delta) {
            this.acceptableSolution = acceptableSolution;
            this.delta = delta;
        }

        @Override
        public boolean checkConvergence( List<Character> population, int generation) {
            double maximumFitness = Character.getMaximumFitness(population);
            return maximumFitness >= acceptableSolution-delta;
        }
}
