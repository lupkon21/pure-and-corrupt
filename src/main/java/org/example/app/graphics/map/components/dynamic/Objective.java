package org.example.app.graphics.map.components.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.graphics.map.components.root.PaintableComponent;

@Getter
@Setter
@AllArgsConstructor
// @NoArgsConstructor
@ToString(callSuper=true)
public class Objective extends PaintableComponent {

    @JsonCreator
    public Objective(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
    }
}
