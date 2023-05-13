package org.example.app.components.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.logic.combat.Combat;
import org.example.app.logic.combat.CombatDetection;
import org.example.app.logic.items.ItemType;
import org.example.app.logic.movement.CollisionDetection;
import org.example.app.logic.movement.Direction;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Loader;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Player extends PaintableComponent {

    private Integer speed;
    private boolean isCombatActive;
    private boolean isDefendActive;

    private Direction direction;
    private Integer hp;
    private Integer attackDamage;
    private Integer attackCooldown;
    private Integer defendCooldown;
    private Integer defendTime;
    private ArrayList<Item> items;
    private ArrayList<Item> activatableItems;

    @JsonCreator
    public Player(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
        speed = MapConstants.GRID_CELL_SIZE;
        isCombatActive = false;
        isDefendActive = false;
        direction = Direction.EAST;
        Loader.loadStats(this);
        this.items = new ArrayList<>();
        this.activatableItems = new ArrayList<>();
        MapConstants.PLAYER_MAX_HP = hp;
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
        CollisionDetection.checkItemCollision();
        checkCombat();
    }

    public void attack() {
        if(isCombatActive) Combat.playerDefaultAttack();
    }
    public void defend() {
        if(isCombatActive) Combat.playerDefend();
    }


    private void changeAsset(Direction direction) {
        if(this.direction.equals(direction)) return;
        this.direction = direction;
        this.setIdAsset(direction.getId());
        this.loadAsset();
    }

    private void checkCombat() {
        Enemy enemy = CombatDetection.isCombatPlayer();
        if(enemy != null) {
            Combat.activatePlayerCombat(enemy);
        } else if(this.isCombatActive()) {
            Combat.deactivatePlayerCombat();
        }
    }

    public void setHp(Integer hp) {
        if(hp > MapConstants.PLAYER_MAX_HP) {
            this.hp = MapConstants.PLAYER_MAX_HP;
            return;
        }
        this.hp = hp;
    }

    public boolean hasItem(ItemType itemType) {
        for(Item item : items) {
            if(item != null && item.getItemType().equals(itemType)) return true;
        }
        return false;
    }

    public boolean hasActivatableItem(ItemType itemType) {
        for(Item item : activatableItems) {
            if(item != null && item.getItemType().equals(itemType)) return true;
        }
        return false;
    }
}
