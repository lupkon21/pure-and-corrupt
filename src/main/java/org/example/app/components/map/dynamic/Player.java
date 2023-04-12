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
public class Player extends PaintableComponent {

    private Integer speed;

    @JsonCreator
    public Player(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
        speed = MapConstants.GRID_CELL_SIZE;
    }

    public void move(Direction direction) {
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
}
