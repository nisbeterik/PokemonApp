package assignment2;

public class Item {
    private final String ITEM_NAME;
    private final int HEALING_POWER;
    private final double WEIGHT;

    public Item(String name, int hp, double weight) {
        this.ITEM_NAME = name;
        this.HEALING_POWER = hp;
        this.WEIGHT = weight;
    }

    public String getItemName() {
        return ITEM_NAME;
    }

    public int getHealingPower() {
        return HEALING_POWER;
    }

    public double getWeight() {
        return WEIGHT;
    }

    public String toString() {
        return String.format("%s heals %d HP. (%.2f)", this.ITEM_NAME, this.HEALING_POWER, convertToTwoDecimal(WEIGHT));
    }


    public boolean equals(Object otherObject) {

        if (otherObject == this) {
            return true;
        } else if (otherObject == null) {
            return false;

        } else if (otherObject instanceof Item) {
            Item otherItem = (Item) otherObject;
            boolean sameName = ITEM_NAME.equals(otherItem.ITEM_NAME);
            boolean sameHealingPower = HEALING_POWER == (otherItem.HEALING_POWER);
            boolean sameWeight = WEIGHT == (otherItem.WEIGHT);
            return sameName && sameHealingPower && sameWeight;

        } else {
            return false;
        }
    }

    private double convertToTwoDecimal(double value) {
        return (int) (value*100) / 100.0;
    }
}