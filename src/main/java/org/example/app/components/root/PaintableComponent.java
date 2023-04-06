package org.example.app.components.root;

import lombok.*;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class PaintableComponent extends DefaultComponent implements Paintable {

    private Integer idAsset;

    @Override
    public void paint(Graphics2D g2) {
    }
}
