package ar.edu.itba;

import java.util.Random;

public class Height extends Genome {

    private double height;
    public static final double MAX_HEIGHT = 2.2;
    public static final double MIN_HEIGHT = 1.3;

    public Height() {
        mutate();
    }

    public Height(double height){
        this.height = height;
    }

    @Override
    public void mutate() {
        Random r = new Random();
       this.height = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * r.nextDouble();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


}
