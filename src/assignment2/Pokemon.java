package assignment2;

public class Pokemon {

    //reference variables for the pokemon attributes, HP = Health Points, EP = Energy Points
    private final int MAX_HP;
    private final  Type TYPE;
    private String name;
    private int currentHP;
    private int currentEP;
    private Skill pokemonSkill;

    //constructor for creating a pokemon
    public Pokemon(String name, int MAX_HP, String TYPE) {
        this.name = name;
        this.MAX_HP = MAX_HP;
        this.currentHP = this.MAX_HP;
        this.currentEP = 100;
        this.TYPE = Type.valueOf(TYPE.toUpperCase());
    }



    public int getMAX_HP() {
        return this.MAX_HP;
    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public String getType() {
        return String.valueOf(this.TYPE);
    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.currentEP;
    }






    //check if pokemon knows skill
    public boolean knowsSkill() {
        if (this.pokemonSkill == null) {
            return false;
        } else {
            return true;
        }
    }

    //set pokemon skill to null
    public void forgetSkill() {
        this.pokemonSkill = null;
    }

    //teaches pokemon a skill using Skills class constructor for adding the attributes of the skill
    public void learnSkill(String name, int attackPower, int energyCost) {
        this.pokemonSkill = new Skill(name, attackPower, energyCost);
    }


    public void rest() {
        final int REST = 20;
        if (this.currentHP > 0) {
            this.currentHP = Math.min(this.currentHP + REST, this.MAX_HP);
        }
    }

    public void recoverEnergy() {
        final int ENERGY_RECOVERY = 25;
        if (this.currentHP > 0) {
            this.currentEP = Math.min(this.currentEP + ENERGY_RECOVERY, 100);
        }
    }

    public void receiveDamage(Pokemon attackingPokemon, double attackPower, double multiplier) {
        int energyCost = attackingPokemon.pokemonSkill.getENERGY_COST();

        if (attackingPokemon.currentEP >= energyCost) {
            this.currentHP = (int) Math.max(Math.round(currentHP - attackPower * multiplier), 0);
            attackingPokemon.currentEP -= energyCost;
        }
    }

    public String attack(Pokemon targetPokemon) {
        String attackCheckResult = checkAttackPossible(targetPokemon);
        if (!attackCheckResult.isEmpty()) {
            return attackCheckResult;
        }

        double multiplier = new TypeMultiplier(TYPE, targetPokemon.TYPE).getMultiplier();
        targetPokemon.receiveDamage(this, pokemonSkill.getAttackPower(), multiplier);
        return buildAttackOutput(targetPokemon, multiplier);
    }

    private String checkAttackPossible(Pokemon targetPokemon) {
        if (this.currentHP == 0) {
            return String.format("Attack failed. %s fainted.", this.name);
        } else if (targetPokemon.currentHP == 0) {
            return String.format("Attack failed. %s fainted.", targetPokemon.name);
        } else if (this.pokemonSkill == null) {
            return String.format("Attack failed. %s does not know a skill.", this.name);
        } else if (this.currentEP < pokemonSkill.getENERGY_COST()) {
            return String.format("Attack failed. %s lacks energy: %d / %d", this.name, this.currentEP, this.pokemonSkill.getENERGY_COST());
        }
        return "";
    }

    private String buildAttackOutput(Pokemon targetPokemon, double multiplier) {
        String effectiveness = "";
        if (multiplier == 0.5) {
            effectiveness = "It is not very effective...";
        } else if (multiplier == 2) {
            effectiveness = "It is super effective!";
        }

        String remainingHP = targetPokemon.currentHP > 0 ? String.format("%s has %d HP left.", targetPokemon.name, targetPokemon.currentHP) : String.format("%s has %d HP left. %s faints.", targetPokemon.name, targetPokemon.currentHP, targetPokemon.name);
        String lineSeparator = System.lineSeparator();

        if (effectiveness.isEmpty()) {
            return String.format("%s uses %s on %s.%s%s", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, lineSeparator, remainingHP);
        } else {
            return String.format("%s uses %s on %s. %s%s%s", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, effectiveness, lineSeparator, remainingHP);
        }
    }


    public String useItem(Item item) {
        if (currentHP == MAX_HP) {
            return String.format("%s could not use %s. HP is already full.", name, item.getItemName());
        }
        int startingHP = currentHP;
        currentHP = currentHP + item.getHealingPower();
        if (getCurrentHP() > getMAX_HP()) {
            currentHP = MAX_HP;
        }
        return String.format("%s used %s. It healed %d HP.", name, item.getItemName(), currentHP - startingHP);


    }

    public String toString() {
        String output;
        if (this.pokemonSkill != null) {
            output = String.format("%s (%s). Knows %s - AP: %d EC: %d", this.name, this.TYPE, pokemonSkill.getSkillName(), pokemonSkill.getAttackPower(), pokemonSkill.getENERGY_COST());
        } else {
            output = String.format("%s (%s)", this.name, this.TYPE);
        }
        return output;
    }
    public boolean equals(Object otherObject) {

        if (otherObject == this) {
            return true;
        } else if (otherObject == null) {
            return false;

        } else if (otherObject instanceof Pokemon && this.pokemonSkill == null) {
            Pokemon otherPokemon = (Pokemon) otherObject;
            boolean sameName = this.name.equals(otherPokemon.name);
            boolean sameMaxHp = this.MAX_HP == otherPokemon.MAX_HP;
            boolean sameType = this.TYPE.equals(otherPokemon.TYPE);
            return sameName && sameMaxHp && sameType;

        }else if (otherObject instanceof Pokemon) {
            Pokemon otherPokemon = (Pokemon) otherObject;

            boolean sameName = this.name.equals(otherPokemon.name);
            boolean sameMaxHp = this.MAX_HP == otherPokemon.MAX_HP;
            boolean sameType = this.TYPE.equals(otherPokemon.TYPE);
            boolean sameSkill = otherPokemon.pokemonSkill.equals(this.pokemonSkill);
            return sameName && sameMaxHp && sameType && sameSkill;
        } else {
            return false;
        }
    }

}

