package assignment2;

import java.util.ArrayList;

public class ItemBag {
    private final double MAX_WEIGHT;
    private ArrayList<Item> itemList;
    private double weightOfBag;

    public ItemBag(double MAX_WEIGHT) {
        this.MAX_WEIGHT = MAX_WEIGHT;
        this.itemList = new ArrayList<>();
        this.weightOfBag = 0;
    }

    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public ArrayList<Item> getItems() {
        return itemList;
    }

    public double getCurrentWeight() {
        return weightOfBag;
    }

    public int getNumOfItems() {
        return itemList.size();
    }


    public int addItem(Item item) {
        if (enoughWeightSpace(item)) {
            return addItemBasedOnWeight(item);
        }
        return -1;
    }



    public Item removeItemAt(int index) {
        if (isIndexOutOfBounds(index)) {
            return null;
        }
        Item removedItem = itemList.get(index);
        removeAndDecreaseWeight(removedItem);
        return removedItem;
    }

    public String peekItemAt(int index) {
        if (isIndexOutOfBounds(index)) {
            return "";
        }
        return String.valueOf(getItems().get(index));

    }

    public Item popItem() {
        if (getNumOfItems() == 0) {
            return null;
        }
        Item heaviestItem = itemList.get(0);
        removeAndDecreaseWeight(heaviestItem);

        return heaviestItem;
    }


    private void addItemIncreaseWeight(int index, Item item) {
        itemList.add(index, item);
        weightOfBag = weightOfBag + item.getWeight();
    }


    private void removeAndDecreaseWeight(Item item) {
        itemList.remove(item);
        weightOfBag = weightOfBag - item.getWeight();
    }

    private boolean isIndexOutOfBounds(int index) {
        return ((getNumOfItems() <= index) || (index < 0));
    }


    private boolean enoughWeightSpace(Item item) {
        return (weightOfBag + item.getWeight()) <= MAX_WEIGHT;
    }


    private double peekAtWeight(int index) {
        return itemList.get(index).getWeight();
    }


    private int addItemBasedOnWeight(Item item) {
        for (int index = 0; index <= (getNumOfItems()); index++) {
            if (index == getNumOfItems() || item.getWeight() >= peekAtWeight(index)) {
                addItemIncreaseWeight(index, item);
                return index;
            }

        }
        return -1;
    }
}