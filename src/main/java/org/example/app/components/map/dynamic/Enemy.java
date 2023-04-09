package org.example.app.components.map.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.root.PaintableComponent;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Enemy extends PaintableComponent {
    private Integer idType;

    @JsonCreator
    public Enemy(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("id_asset") Integer idAsset, @JsonProperty("id_type") Integer idType) {
        super(x,y,idAsset);
        this.idType = idType;
    }
}
