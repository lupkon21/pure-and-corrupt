package org.example.app.logic.combat;

public enum CombatAction {
    DEFAULT_ATTACK(1),
    ITEM_ATTACK(2),
    DEFEND(3);

    private final Integer id;
    CombatAction(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
