package assignment2;


public enum Type {
    BUG("Bug"),
    DRAGON("Dragon"),
    ELECTRIC("Electric"),
    FIRE("Fire"),
    GRASS("Grass"),
    ICE("Ice"),
    WATER("Water"),
    NORMAL("Normal");


    private final String TYPE_NAME;


    Type(String typeName) {
        this.TYPE_NAME = typeName;
    }


    public String toString() {
        return TYPE_NAME;
    }

}

