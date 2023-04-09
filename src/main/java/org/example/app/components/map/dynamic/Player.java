package org.example.app.components.map.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.root.PaintableComponent;

@Getter
@Setter
@AllArgsConstructor
// @NoArgsConstructor
@ToString(callSuper=true)
public class Player extends PaintableComponent {

    @JsonCreator
    public Player(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset) {
        super(x,y,idAsset);
    }
}
