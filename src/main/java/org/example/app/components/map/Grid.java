package org.example.app.components.map;

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
