package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;

public class ProbabilisticTournament extends Selection {
    private double threshold;

    public ProbabilisticTournament(double threshold) {

        this.threshold = threshold;
    }

    @Override
    public List<Character> select(int selectionSize, List<Character> population,int generation) {
        if(selectionSize == 0){
            return null;
        }
        if(threshold < 0.5 || threshold >1){
            throw new IllegalArgumentException();
        }
        int k = 0;
        int size = population.size();
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            Character winner;

            int random1 = (int) (size*Math.random());
            int random2 = (int) (size*Math.random());
            Character aux1 = population.get(random1);
            Character aux2 = population.get(random2);

            double randomThreshold = (size*Math.random());

            if(randomThreshold < threshold){
                winner = aux1.getFitness() > aux2.getFitness() ? aux1 : aux2;

            }else{
                winner = aux1.getFitness() < aux2.getFitness() ? aux1 : aux2;
            }

            selection.add(winner);
            k++;
        }
        return selection;
    }
}
