package ar.edu.itba;

import java.util.Objects;
import java.util.Random;

public class Height implements Genome, Cloneable {

    private double height;
    public static final double MAX_HEIGHT = 2.0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height1 = (Height) o;
        return Double.compare(height1.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }

    @Override
    public String toString() {
        return "Height{" +
                "height=" + height +
                '}';
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
