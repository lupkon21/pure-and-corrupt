package org.example.app.logic.items;

public enum ItemType {
    DEFAULT(0),
    LIFECRYSTAL(1),
    SWORDBREAK(2),
    EYEWHIP(3),
    THORNPARTY(4),
    CORRUPTED_BOOTS(5),
    MIGHTY_TOOTH(6),
    FLESH_CROSS(7);

    private final Integer id;

    ItemType(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ItemType getById(Integer id) {
        for(ItemType i : ItemType.values()) {
            if(i.id.equals(id)) return i;
        }
        return ItemType.DEFAULT;
    }
}
