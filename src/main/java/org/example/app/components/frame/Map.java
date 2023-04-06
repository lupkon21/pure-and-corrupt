package org.example.app.components.frame;

import lombok.*;
import org.example.app.components.map.Components;
import org.example.app.components.map.Grid;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Map {
    private Integer id;
    private String name;
    private Grid grid;
    private Components components;
}
