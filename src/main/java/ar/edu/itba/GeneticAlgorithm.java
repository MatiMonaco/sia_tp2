package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GeneticAlgorithm {


    private List<Character> population;
    private CharacterType characterType;
    private Function<List<Character>,Boolean> checkConverge;
    private int generation;

    public GeneticAlgorithm(int initialSize, CharacterType characterType){
        this.characterType = characterType;
        loadInitialPopulation(initialSize,characterType);
        generation = 0;
    }

    private void loadInitialPopulation(int initialSize,CharacterType characterType){
        for(int i = 0; i < initialSize;i++){

            String types[] = {"helmet","armor","weapon","gloves","boots"};
            List<Equipment> equipment = new ArrayList<>();
            for(int j = 0; j < types.length;j++){
                Equipment eq = Database.getRandomEquipment(types[j]);
                equipment.add(eq);
            }
            Character character = new Character(characterType,new Height(),equipment);
            population.add(character);
        }
    }
    public void start(){
        while(checkConverge.apply(population)){

        }
    }
}
