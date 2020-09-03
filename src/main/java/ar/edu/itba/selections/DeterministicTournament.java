package ar.edu.itba.selections;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;

public class DeterministicTournament  extends Selection{

    private int M;

    public DeterministicTournament(int m) {

        M = m;
    }

    @Override
    public List<Character> select( int selectionSize,List<Character> population,int generation) {
//        System.out.println("inicio det tournament");
        if(selectionSize == 0){
            return null;
        }
        int k = 0;
        int size = population.size();
        List<Character> selection = new ArrayList<>();
        while(k < selectionSize){
            int m = 0;
            Character winner = null;
            while(m< M){
                int random = (int) (size*Math.random());

                Character aux = population.get(random);
                if(winner == null) {
                    winner = aux;
                }else if(aux.getFitness() > winner.getFitness()){
                    winner = aux;
                }
                m++;

            }

            selection.add(winner);
            k++;
        }
//        System.out.println("return det tournament");
        return selection;
    }
}
