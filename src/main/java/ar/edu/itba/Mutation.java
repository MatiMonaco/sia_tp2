package ar.edu.itba;

import ar.edu.itba.classes.Character;

import java.util.Random;
import java.util.function.DoublePredicate;

public class Mutation {

    public static Void genMutation(Character character, Double pm){
        Random r = new Random();
        int gen = r.nextInt(character.getGenomes().size()); // which gen
//        System.out.println("gen number: " + gen);

        if(r.nextDouble() < pm){
//            System.out.println("mutating...");
            character.mutateGen(gen);
        }
        return null;
    }

    public static Void multiGenMutation(Character character, Double pm){
        Random r = new Random();
        int gens = r.nextInt(character.getGenomes().size()); // how many gens

        for (int i = 0; i < gens; i++) {
            int g = r.nextInt(character.getGenomes().size());
            if (r.nextDouble() < pm){

                character.mutateGen(g);
            }
        }
        return null;
    }

    public static Void multiGenUniform(Character character, Double pm){
        Random r = new Random();

        for(int i = 0; i < character.getGenomes().size(); i++){
            if (r.nextDouble() < pm){

                character.mutateGen(i);
            }
        }
        return null;
    }

    public static Void complete(Character character, Double pm){
        Random r = new Random();

        if (r.nextDouble() < pm){
            for(int i = 0; i < character.getGenomes().size(); i++){
                character.mutateGen(i);
            }
        }
        return null;
    }

}
