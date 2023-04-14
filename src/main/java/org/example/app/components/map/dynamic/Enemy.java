package org.example.app.components.map.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.map.movement.CollisionDetection;
import org.example.app.components.map.movement.Direction;
import org.example.app.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Enemy extends PaintableComponent {
    private Integer idType;
    private Direction direction;
    private Integer speed;

    @JsonCreator
    public Enemy(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset, @JsonProperty("id_type") Integer idType) {
        super(x,y,idAsset);
        this.idType = idType;
        this.direction = Direction.EAST;
        this.speed = MapConstants.GRID_CELL_SIZE;
    }

    public void move() {
        if(direction.equals(Direction.EAST)) {
            x += speed;
            if(CollisionDetection.isCollision(this)) {
                x -= speed;
                direction = Direction.WEST;
            }
        } else if(direction.equals(Direction.WEST)) {
            x -= speed;
            if(CollisionDetection.isCollision(this)) {
                x += speed;
                direction = Direction.EAST;
            }
        }
    }
}
