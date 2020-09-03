package ar.edu.itba.convergences;

import ar.edu.itba.classes.Character;

import java.util.List;

public class TimeConvergence extends Convergence
{
    private long secondsLimit;
    private long startTime;
    public TimeConvergence(long secondsLimit,long startTime) {
        this.secondsLimit = secondsLimit;
        this.startTime = startTime;
    }


    @Override
    public boolean checkConvergence(List<Character> population, int generation) {
        long now = System.currentTimeMillis()/1000;

        long diff = now - startTime;

        System.out.println("Now: "+now+ " StartTime: "+startTime+" diff: "+diff);
        return diff >= secondsLimit;
    }
}
