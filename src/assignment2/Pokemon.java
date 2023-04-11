package assignment2;

public class Pokemon {

    //reference variables for the pokemon attributes, HP = Health Points, EP = Energy Points
    private final int MAX_HP;
    private final  Type TYPE;
    private String name;
    private int currentHP;
    private int currentEP;
    private Skills pokemonSkill;

    //constructor for creating a pokemon
    public Pokemon(String name, int MAX_HP, String TYPE) {
        this.name = name;
        this.MAX_HP = MAX_HP;
        this.currentHP = this.MAX_HP;
        this.currentEP = 100;
        this.TYPE = Type.valueOf(TYPE.toUpperCase());
    }


    //getters (no setters needed since setting various attributes is done within other methods)
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

    //prints pokemon information (if statement to check if pokemon knows skill or not)
    public String toString() {
        String output;
        if (this.pokemonSkill != null) {
            output = String.format("%s (%s). Knows %s - AP: %d EC: %d", this.name, this.TYPE, pokemonSkill.getSkillName(), pokemonSkill.getAttackPower(), pokemonSkill.getENERGY_COST());
        } else {
            output = String.format("%s (%s)", this.name, this.TYPE);
        }
        return output;
    }

    //equals method(if statements for checking if the other object is null/an instance of the other pokemon/if pokemon knows skill
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
        this.pokemonSkill = new Skills(name, attackPower, energyCost);
    }

    //rests pokemon, checks so currentHP doesn't exceed max HP and that pokemon is not fainted
    public void rest(){
        final int REST = 20;
        if(this.currentHP + REST > MAX_HP) {
            this.currentHP = this.MAX_HP;
        } else if (this.currentHP > 0) {
            this.currentHP = this.currentHP + REST;
        }
    }

    //recovers energy, checks so pokemon is not fainted and that current energy points does not exceed max energy points
    public void recoverEnergy() {
        if(this.currentHP > 0 && this.currentEP <= 75) {
            this.currentEP = this.currentEP + 25;
        } else if (this.currentEP > 75){
            this.currentEP = 100;
        }
    }


    public void receiveDamage(Pokemon attackingPokemon, double attackPower, double multiplier) {

        //setting local variables for readability in the if-statements and avoiding calculations in if-statements
        int targetHP = this.currentHP;
        int energyCost = attackingPokemon.pokemonSkill.getENERGY_COST();
        attackPower = attackPower * multiplier;
        int currentEnergyPoints = attackingPokemon.currentEP;

        if(targetHP > attackPower && energyCost <= currentEnergyPoints) {
            this.currentHP = (int) Math.round(currentHP - attackPower);
            attackingPokemon.currentEP = currentEnergyPoints - energyCost;
        } else if(Math.round(targetHP) < attackPower && currentEnergyPoints >= energyCost){
            this.currentHP = 0;
            attackingPokemon.currentEP = currentEnergyPoints - energyCost;
        }
    }

    public String attack(Pokemon targetPokemon) {

        double multiplier = new TypeMultiplier(TYPE, targetPokemon.TYPE).getMultiplier();
        String notEffective = "It is not very effective..." ;
        String superEffective = "It is super effective!";
        String optFaints = String.format("%s faints.", targetPokemon.name);
        String attackOutput = "";

        //checks if attack is possible based on target/attacking pokemon attributes
        if (this.currentHP == 0) {
            attackOutput = String.format("Attack failed. %s fainted.", this.name);
            return attackOutput;
        } else if (targetPokemon.currentHP == 0) {
            attackOutput = String.format("Attack failed. %s fainted.", targetPokemon.name);
            return attackOutput;
        } else if (this.pokemonSkill == null) {
            attackOutput = String.format("Attack failed. %s does not know a skill.", this.name);
        } else if (this.currentEP < pokemonSkill.getENERGY_COST()) {
            attackOutput = String.format("Attack failed. %s lacks energy: %d / %d", this.name, this.currentEP, this.pokemonSkill.getENERGY_COST());
            return attackOutput;
        }

        //if an attack is possible calls method for targetPokemon to recieve damage
        targetPokemon.receiveDamage(this, pokemonSkill.getAttackPower(), multiplier);

        // cast multiplier to integer so it works with switch case, formats string output damage based on damage done/if pokemon faints
        switch ((int) multiplier) {
            case 0:
                if (targetPokemon.currentHP > 0) {
                    attackOutput = String.format("%s uses %s on %s. %s%n%s has %d HP left.", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, notEffective, targetPokemon.name, targetPokemon.currentHP);
                } else {
                    attackOutput = String.format("%s uses %s on %s. %s%n%s has %d HP left. %s", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, notEffective, targetPokemon.name, targetPokemon.currentHP, optFaints);
                }
                return attackOutput;
            case 2:
                if (targetPokemon.currentHP > 0) {
                    attackOutput = String.format("%s uses %s on %s. %s%n%s has %d HP left.", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, superEffective, targetPokemon.name, targetPokemon.currentHP);
                } else {
                    attackOutput = String.format("%s uses %s on %s. %s%n%s has %d HP left. %s", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, superEffective, targetPokemon.name, targetPokemon.currentHP, optFaints);
                }
                return attackOutput;
            default:
                if (targetPokemon.currentHP > 0) {
                    attackOutput = String.format("%s uses %s on %s.%n%s has %d HP left.", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, targetPokemon.name, targetPokemon.currentHP);
                } else {
                    attackOutput = String.format("%s uses %s on %s.%n%s has %d HP left. %s", this.name, this.pokemonSkill.getSkillName(), targetPokemon.name, targetPokemon.name, targetPokemon.currentHP, optFaints);
                }
                return attackOutput;
        }
    }


    public String useItem(Item item) {
        if (currentHP == MAX_HP) {
            return String.format("%s could not use %s. HP is already full.", name, item.getItemName());
        }
        int startingHP = currentHP;
        //heal the pokemon
        currentHP = currentHP + item.getHealingPower();
        if (getCurrentHP() > getMAX_HP()) {
            currentHP = MAX_HP;
        }
        return String.format("%s used %s. It healed %d HP.", name, item.getItemName(), currentHP - startingHP);


    }


}

