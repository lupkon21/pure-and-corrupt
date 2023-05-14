package org.example.app.components.map.components.root;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.constants.MapConstants;
import org.example.app.logic.render.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class PaintableComponent extends DefaultComponent implements Paintable {

    protected Integer idAsset;
    @JsonIgnore
    protected BufferedImage asset;
    protected Integer idAssetDir;

    @JsonCreator
    public PaintableComponent(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x, y);
        idAssetDir = idAsset;
        this.idAsset = 1;
        if(idAssetDir != 2 && idAssetDir != 3) loadAsset();
    }

    @Override
    public void paint(Graphics2D g2) {
        if(asset == null) return;
        g2.drawImage(asset, x, y, MapConstants.GRID_CELL_SIZE, MapConstants.GRID_CELL_SIZE, null );
    }

    public void loadAsset() {
        if(idAsset == null || idAssetDir == null) return;
        asset = Loader.loadAsset(MapConstants.ASSET_PATH + idAssetDir + "/" + idAsset + ".png");
    }
}
