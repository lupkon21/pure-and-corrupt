package org.example.app.components.map.game;

import lombok.*;
import org.example.app.components.root.PaintableComponent;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Item extends PaintableComponent {
    private Integer idType;
}
