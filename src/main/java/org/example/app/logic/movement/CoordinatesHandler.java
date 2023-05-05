package org.example.app.logic.movement;

import org.example.app.constants.MapConstants;

import java.util.Objects;

public class CoordinatesHandler {
    public static Point moveCoordinates(Point point, Direction direction) {
        Point p = new Point(point.getX(), point.getY());

        if (Objects.requireNonNull(direction) == Direction.NORTH) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.EAST) {
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTH) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.WEST) {
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.NORTHEAST) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTHEAST) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() + MapConstants.GRID_CELL_SIZE);
        } else if (Objects.requireNonNull(direction) == Direction.SOUTHWEST) {
            p.setY(point.getY() + MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        }  else if (Objects.requireNonNull(direction) == Direction.NORTHWEST) {
            p.setY(point.getY() - MapConstants.GRID_CELL_SIZE);
            p.setX(point.getX() - MapConstants.GRID_CELL_SIZE);
        }

        return p;
    }
}
