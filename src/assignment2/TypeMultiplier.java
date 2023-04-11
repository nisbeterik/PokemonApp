package assignment2;

public class TypeMultiplier {

    private final Type ATTACKER;

    private final Type DEFENDER;
    //row is attacker, column is defender
    private final double[][] multiTable = {
            {1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0},
            {1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
            {1.0, 0.5, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0},
            {2.0, 0.5, 1.0, 0.5, 2.0, 2.0, 0.5, 1.0},
            {0.5, 0.5, 1.0, 0.5, 0.5, 1.0, 2.0, 1.0},
            {1.0, 2.0, 1.0, 0.5, 2.0, 0.5, 0.5, 1.0},
            {1.0, 0.5, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0},
            {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}
    };

    public TypeMultiplier(Type attacker, Type defender) {
        this.ATTACKER = attacker;
        this.DEFENDER = defender;
    }

    //calls the tableChecker method and returns index for attacker, then offender and gets correct multiplier from table
    public double getMultiplier() {
        return multiTable[tableChecker(ATTACKER)][tableChecker(DEFENDER)];
    }

    //return value represents index of row or column in multiTable method
    private int tableChecker(Type type) {

        switch (type) {
            case BUG:
                return 0;
            case DRAGON:
                return 1;
            case ELECTRIC:
                return 2;
            case FIRE:
                return 3;
            case GRASS:
                return 4;
            case ICE:
                return 5;
            case WATER:
                return 6;
            case NORMAL:
                return 7;
            default:
                return -1;
        }
    }
}
