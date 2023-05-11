package org.example.app.components.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.combat.CombatDetection;
import org.example.app.logic.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Enemy extends PaintableComponent implements ActionListener {
    private Integer idType;
    private Direction direction;
    private Integer speed;
    private boolean isCombatActive;
    private Integer hp;
    private Integer attackDamage;
    private Integer attackCooldown;
    private Timer attackTimer;

    @JsonCreator
    public Enemy(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset, @JsonProperty("id_type") Integer idType, @JsonProperty("id_type_movement") Integer idTypeMovement) {
        super(x,y,idAsset);
        this.idType = idType;
        this.direction = getMovementDirectionById(idTypeMovement);
        loadAsset();
        this.changeAsset(direction);
        this.speed = MapConstants.GRID_CELL_SIZE;
        this.isCombatActive = false;
        Loader.loadStats(this);
        this.attackTimer = new Timer(attackCooldown, this);
        this.attackTimer.start();
    }

    public void move() {
        isCombatActive = CombatDetection.isCombatEnemy(this);
        if(direction == null || isCombatActive) return;

        if(direction.equals(Direction.EAST)) {
            x += speed;
            if(CollisionDetection.isCollision(this)) {
                x -= speed;
                direction = Direction.WEST;
                changeAsset(direction);
            }
        } else if(direction.equals(Direction.WEST)) {
            x -= speed;
            if(CollisionDetection.isCollision(this)) {
                x += speed;
                direction = Direction.EAST;
                changeAsset(direction);
            }
        } else if(direction.equals(Direction.NORTH)) {
            y -= speed;
            if(CollisionDetection.isCollision(this)) {
                y += speed;
                direction = Direction.SOUTH;
                changeAsset(direction);
            }
        } else if(direction.equals(Direction.SOUTH)) {
            y += speed;
            if(CollisionDetection.isCollision(this)) {
                y -= speed;
                direction = Direction.NORTH;
                changeAsset(direction);
            }
        }
    }

    private Direction getMovementDirectionById(Integer id) {
        if (id.equals(1)) {
            return Direction.EAST;
        }
        else if(id.equals(2)) {
            return Direction.SOUTH;
        }
        return null;
    }

    public void changeAsset(Direction direction) {
        direction = direction != null ? direction : Direction.SOUTH;
        this.setIdAsset(direction.getId());
        this.loadAsset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isCombatActive) Combat.enemyAttack(this);
    }

    @Override
    public void loadAsset() {
        if(this.idAsset == null || this.idAssetDir == null || idType == null) return;
        this.asset = Loader.loadAsset(MapConstants.ASSET_PATH + this.idAssetDir + "/" + idType + "/" + this.idAsset  + ".png");
    }
}
