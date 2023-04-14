package org.example.app.components.root;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.constants.MapConstants;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DefaultComponent {
    protected Integer x;
    protected Integer y;

    @JsonCreator
    public DefaultComponent(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y) {
        this.x = x * MapConstants.GRID_CELL_SIZE;
        this.y = y * MapConstants.GRID_CELL_SIZE;
    }
}

