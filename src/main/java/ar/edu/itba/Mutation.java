package ar.edu.itba;

import ar.edu.itba.classes.Character;

import java.util.Random;
import java.util.function.DoublePredicate;

public class Mutation {

    public static void genMutation(Character character, Double pm){
        Random r = new Random();
        int gen = r.nextInt(character.getGenomes().size()); // which gen
        System.out.println("gen number: " + gen);

        if(r.nextDouble() < pm){
            System.out.println("mutating...");
            character.mutateGen(gen);
        }
    }

    public static void multiGenMutation(Character character, Double pm){
        Random r = new Random();
        int gens = r.nextInt(character.getGenomes().size()); // how many gens
        System.out.println("mutating " + gens + " gens");
        for (int i = 0; i < gens; i++) {
            int g = r.nextInt(character.getGenomes().size());
            if (r.nextDouble() < pm){
                System.out.println("Mutating gen " + g);
                character.mutateGen(g);
            }
        }
    }

    public static void multiGenUniform(Character character, Double pm){
        Random r = new Random();

        for(int i = 0; i < character.getGenomes().size(); i++){
            if (r.nextDouble() < pm){
                System.out.println("mutating..." + i);
                character.mutateGen(i);
            }
        }
    }

    public static void complete(Character character, Double pm){
        Random r = new Random();

        if (r.nextDouble() < pm){
            for(int i = 0; i < character.getGenomes().size(); i++){
                character.mutateGen(i);
            }
        }
    }
}
