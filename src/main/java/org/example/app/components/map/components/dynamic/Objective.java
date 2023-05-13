package org.example.app.components.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.map.components.root.PaintableComponent;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Objective extends PaintableComponent {

    private boolean isVisible;

    @JsonCreator
    public Objective(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
        isVisible = false;
    }

    @Override
    public void paint(Graphics2D g2) {
        if(isVisible) super.paint(g2);
    }
}
