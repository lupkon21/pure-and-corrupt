package org.example.app.logic.movement;

public enum Direction {
    NORTH(3),
    EAST(1),
    SOUTH(4),
    WEST(2),
    NORTHEAST(5),
    SOUTHEAST(6),
    SOUTHWEST(7),
    NORTHWEST(8);

    private final Integer id;

    Direction(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
