package org.example.app.components.map.dynamic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.app.components.root.DefaultComponent;

@Getter
@Setter
@ToString(callSuper=true)
public class PatrolPoint extends DefaultComponent {

    @JsonCreator
    public PatrolPoint(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y) {
        super(x,y);
    }
}
