package ar.edu.itba;

public class Main {

    public static void main(String[] args) {
        Character warrior = new Character(Character.CharacterClass.WARRIOR);

        System.out.println(warrior.type);
        System.out.println(warrior.type.getBaseAttackM());
        System.out.println(warrior.type.getBaseDefenseM());
    }
}
