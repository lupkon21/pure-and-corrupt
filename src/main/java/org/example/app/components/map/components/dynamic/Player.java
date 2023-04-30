package org.example.app.components.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Player extends PaintableComponent {

    private Integer speed;
    private boolean isCombatActive;
    private Direction direction;

    @JsonCreator
    public Player(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
        speed = MapConstants.GRID_CELL_SIZE;
        isCombatActive = false;
        direction = Direction.EAST;
    }

    public void move(Direction direction) {
        changeAsset(direction);
        if(direction.equals(Direction.NORTH)) {
            y -= speed;
            if(CollisionDetection.isCollision(this)) y += speed;
        } else if(direction.equals(Direction.EAST)) {
            x += speed;
            if(CollisionDetection.isCollision(this)) x -= speed;
        } else if(direction.equals(Direction.SOUTH)) {
            y += speed;
            if(CollisionDetection.isCollision(this)) y -= speed;
        } else if(direction.equals(Direction.WEST)) {
            x -= speed;
            if(CollisionDetection.isCollision(this)) x += speed;
        }
    }

    public void attack() {
        if(isCombatActive) Combat.playerDefaultAttack();
    }


    private void changeAsset(Direction direction) {
        if(this.direction.equals(direction)) return;

        this.direction = direction;
        this.setIdAsset(direction.getId());
        this.loadAsset();
    }
}
