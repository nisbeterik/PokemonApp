package assignment2;

public class Skill {


    final private int ENERGY_COST;
    final private int ATTACK_POWER;
    final private String NAME;


    Skill(String name, int attackPower, int energyCost) {
        this.ENERGY_COST = energyCost;
        this.ATTACK_POWER = attackPower;
        this.NAME = name;
    }


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