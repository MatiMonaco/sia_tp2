package ar.edu.itba.classes;

public enum  CharacterType {

    WARRIOR(0.6, 0.6),
    ARCHER(0.9, 0.1),
    DEFENDER(0.3, 0.8),
    INFILTRATOR(0.8, 0.3);

    private final double baseAttackM, baseDefenseM;

    CharacterType(double baseAttackM, double baseDefenseM) {
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
