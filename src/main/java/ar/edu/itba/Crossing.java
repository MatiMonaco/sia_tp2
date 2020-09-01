package ar.edu.itba;

import ar.edu.itba.classes.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crossing {
    
    int numOfGenomes;

    public Crossing(int numOfGenomes) {
        this.numOfGenomes = numOfGenomes;
    }

    public static List<Character> onePointCrossing(Character parent1, Character parent2){

        Random r = new Random();
        int p = r.nextInt(parent1.getGenomes().size() + 1);
        System.out.printf("VALOR DE P: %d ------------------", p);
        int i = 0;
        List<Genome> genomes1 = new ArrayList<>();
        List<Genome> genomes2 = new ArrayList<>();

        for (Genome g: parent1.getGenomes()) {
            if (i < p){
                genomes1.add(g);
            }else{
                genomes2.add(g);
            }
            i++;
        }

        i = 0;
        for (Genome g: parent2.getGenomes()) {
            if (i < p){
                genomes2.add(g);
            }else{
                genomes1.add(g);
            }
            i++;
        }

        ArrayList<Character> toRet = new ArrayList<>();
        toRet.add(new Character(parent1.getType(), genomes1));
        toRet.add(new Character(parent1.getType(), genomes2));
        return toRet;
    }

    public static List<Character> twoPointCrossing(Character parent1, Character parent2){
        Random r = new Random();
        int p1 = r.nextInt(parent1.getGenomes().size() + 1);
        int p2 = r.nextInt(parent1.getGenomes().size() + 1);
        System.out.printf("VALOR DE P1: %d ------------------", p1);
        System.out.printf("VALOR DE P2: %d ------------------", p2);

        List<Genome> genomes1 = new ArrayList<>();
        List<Genome> genomes2 = new ArrayList<>();

        if (p1>=p2){
            int aux=p1;
            p1=p2;
            p2=p1;
        }

        int i = 0;
        for(Genome g : parent1.getGenomes()){
            if (!(i >= p1 && i <= p2)){
                genomes1.add(g);
            }else{
                genomes2.add(g);
            }
            i++;
        }

        i = 0;
        for(Genome g : parent2.getGenomes()){
            if (!(i >= p1 && i <= p2)){
                genomes2.add(g);
            }else{
                genomes1.add(g);
            }
            i++;
        }

        ArrayList<Character> toRet = new ArrayList<>();
        toRet.add(new Character(parent1.getType(), genomes1));
        toRet.add(new Character(parent1.getType(), genomes2));
        return toRet;
    }

    public static List<Character> annularCrossing(Character parent1, Character parent2){
        Random r = new Random();
        int p = r.nextInt(parent1.getGenomes().size());
        int l = r.nextInt((int)Math.ceil((parent1.getGenomes().size() + 1.0)/2) + 1);
        System.out.printf("VALOR DE P: %d ------------------", p);
        System.out.printf("VALOR DE L: %d ------------------", l);

        List<Genome> parentGens1 = parent1.getGenomes();
        List<Genome> parentGens2 = parent2.getGenomes();

        List<Genome> genomes1 = new ArrayList<>(parentGens1);
        List<Genome> genomes2 = new ArrayList<>(parentGens2);

        for (int i = 0; i < l; i++) {
            if (p+i < parentGens1.size()){
                genomes1.set(p+i, parentGens2.get(p+i));
                genomes2.set(p+i, parentGens1.get(p+i));
            }else{
                int idx = p + i - parentGens1.size();
                genomes1.set(idx, parentGens2.get(idx));
                genomes2.set(idx, parentGens1.get(idx));
            }
        }

        ArrayList<Character> toRet = new ArrayList<>();
        toRet.add(new Character(parent1.getType(), genomes1));
        toRet.add(new Character(parent1.getType(), genomes2));
        return toRet;
    }

    public static List<Character> uniformCrossing(Character parent1, Character parent2){
        double P = 0.5;
        Random r = new Random();

        List<Genome> parentGens1 = parent1.getGenomes();
        List<Genome> parentGens2 = parent2.getGenomes();

        List<Genome> genomes1 = new ArrayList<>(parentGens1);
        List<Genome> genomes2 = new ArrayList<>(parentGens2);

        for (int i = 0; i < parent1.getGenomes().size(); i++) {

            double prob = r.nextDouble();
            //System.out.printf("prob: %g --- idx: %d", prob, i);
            if (prob < P){
                genomes1.set(i, parentGens2.get(i));
                genomes2.set(i, parentGens1.get(i));
            }
        }

        ArrayList<Character> toRet = new ArrayList<>();
        toRet.add(new Character(parent1.getType(), genomes1));
        toRet.add(new Character(parent1.getType(), genomes2));
        return toRet;
    }
}
