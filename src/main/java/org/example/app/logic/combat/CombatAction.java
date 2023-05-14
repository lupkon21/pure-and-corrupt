package org.example.app.logic.combat;

public enum CombatAction {
    ITEM_ATTACK_1(1),
    ITEM_ATTACK_2(2),
    DEFAULT_ATTACK(3),
    DEFEND(4);

    private final Integer id;
    CombatAction(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
