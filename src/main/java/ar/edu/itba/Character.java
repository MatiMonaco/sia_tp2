package ar.edu.itba;

public class Character {

    public enum CharacterClass{
        WARRIOR(0.6, 0.6),
        ARCHER(0.9, 0.1),
        DEFENDER(0.3, 0.8),
        INFILTRATOR(0.8, 0.3);

        private final double baseAttackM, baseDefenseM;

        CharacterClass(double baseAttackM, double baseDefenseM) {
            this.baseAttackM = baseAttackM;
            this.baseDefenseM = baseDefenseM;
        }

        public double getBaseAttackM() {
            return baseAttackM;
        }

        public double getBaseDefenseM() {
            return baseDefenseM;
        }
    }

    CharacterClass type;

    public Character(CharacterClass type) {
        this.type = type;
    }
}
