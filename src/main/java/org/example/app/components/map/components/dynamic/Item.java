package org.example.app.components.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.map.components.root.PaintableComponent;
import org.example.app.constants.MapConstants;
import org.example.app.logic.items.ItemType;
import org.example.app.logic.items.ItemsExecutor;
import org.example.app.logic.render.Loader;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Item extends PaintableComponent {

    private Integer idType;
    private ItemType itemType;
    private boolean activatable;

    @JsonCreator
    public Item(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset, @JsonProperty("id_type") Integer idType) {
        super(x,y,idAsset);
        this.idType = idType;
        this.itemType = ItemType.getById(idType);
        if(idType == ItemType.EYEWHIP.getId() || idType == ItemType.SWORDBREAK.getId()) activatable = true;
        this.loadAsset();
    }

    @Override
    public void loadAsset() {
        if(this.idAssetDir == null || idType == null) return;
        this.asset = Loader.loadAsset(MapConstants.ASSET_PATH + this.idAssetDir + "/" + idType  + ".png");
    }

    public void execute() {
        ItemsExecutor.execute(this);
    }
}
