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

    private static List<Genome> cloneGenomes(Character parent){
        List<Genome> parentGenomes = parent.getGenomes();
        List<Genome> newGenomes = new ArrayList<>();

        try{
            Height old = (Height) parentGenomes.get(0);
            Height newHeight = (Height) old.clone();
            newGenomes.add(newHeight);

            for (int i = 1; i < parentGenomes.size(); i++) {
                Equipment e = (Equipment) parentGenomes.get(i);
                newGenomes.add((Equipment) e.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newGenomes;
    }

    public static List<Character> onePointCrossing(Character parent1, Character parent2){

        Random r = new Random();
        int p = r.nextInt(parent1.getGenomes().size() + 1);
//        System.out.printf("VALOR DE P: %d ------------------", p);

        List<Genome> genomes1 = cloneGenomes(parent1);
        List<Genome> genomes2 = cloneGenomes(parent2);

        for (int i = p; i < parent1.getGenomes().size(); i++) {
            Genome aux = genomes1.get(i);
            genomes1.set(i, genomes2.get(i));
            genomes2.set(i, aux);
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

        List<Genome> genomes1 = cloneGenomes(parent1);
        List<Genome> genomes2 = cloneGenomes(parent2);

        if (p1>p2){
            int aux=p1;
            p1=p2;
            p2=aux;
        }

        for (int i = p1; i < parent1.getGenomes().size() && i < p2; i++) {
            Genome aux = genomes1.get(i);
            genomes1.set(i, genomes2.get(i));
            genomes2.set(i, aux);
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

        List<Genome> genomes1 = cloneGenomes(parent1);
        List<Genome> genomes2 = cloneGenomes(parent2);

        for (int i = 0; i < l; i++) {
            if (p+i < parent1.getGenomes().size()){
                Genome aux = genomes1.get(p+i);
                genomes1.set(p+i, genomes2.get(p+i));
                genomes2.set(p+i, aux);
            }else{
                int idx = p + i - parent1.getGenomes().size();
                Genome aux = genomes1.get(idx);
                genomes1.set(idx, genomes2.get(idx));
                genomes2.set(idx, aux);
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

        List<Genome> genomes1 = cloneGenomes(parent1);
        List<Genome> genomes2 = cloneGenomes(parent2);

        for (int i = 0; i < parent1.getGenomes().size(); i++) {

            double prob = r.nextDouble();
            if (prob < P){
                Genome aux = genomes1.get(i);
                genomes1.set(i, genomes2.get(i));
                genomes2.set(i, aux);
            }
        }

        ArrayList<Character> toRet = new ArrayList<>();
        toRet.add(new Character(parent1.getType(), genomes1));
        toRet.add(new Character(parent1.getType(), genomes2));
        System.out.println("1:"+toRet.get(0).getGenomes()+"\n2:"+toRet.get(1).getGenomes());
        return toRet;
    }
}
