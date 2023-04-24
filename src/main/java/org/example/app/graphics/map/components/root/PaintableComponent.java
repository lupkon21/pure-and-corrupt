package org.example.app.graphics.map.components.root;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.constants.MapConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class PaintableComponent extends DefaultComponent implements Paintable {
    private Integer idAsset;
    @JsonIgnore
    private BufferedImage asset;

    @JsonCreator
    public PaintableComponent(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x, y);
        this.idAsset = idAsset;
        loadAsset();
    }

    @Override
    public void paint(Graphics2D g2) {
        if(idAsset != null) loadAsset();
        g2.drawImage(asset, x, y, MapConstants.GRID_CELL_SIZE, MapConstants.GRID_CELL_SIZE, null );
    }

    private void loadAsset() {
        if(idAsset == null) return;
        try {
            asset = ImageIO.read(new File(MapConstants.ASSET_PATH + idAsset + ".jpg"));
        } catch (IOException e) {
            System.out.println("Image for idAsset=" + idAsset + " not found.");
        }
    }
}
