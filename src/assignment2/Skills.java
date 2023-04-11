package assignment2;

public class Skills {

    //reference variables for skills attributes
    final private int ENERGY_COST;
    final private int ATTACK_POWER;
    final private String NAME;

    //constructor for skills
    Skills(String name, int attackPower, int energyCost) {
        this.ENERGY_COST = energyCost;
        this.ATTACK_POWER = attackPower;
        this.NAME = name;
    }

    //getters (no setters needed since all reference variables are final and cannot be changed)
    public String getSkillName() {
        return this.NAME;
    }

    public int getAttackPower() {
        return this.ATTACK_POWER;
    }

    public int getENERGY_COST() {
        return this.ENERGY_COST;
    }

}