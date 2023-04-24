package org.example.app.graphics.map.components;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grid {
    private Integer x;
    private Integer y;
    private Integer cellSize;
}
