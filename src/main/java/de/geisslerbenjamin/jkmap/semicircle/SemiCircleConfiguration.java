package de.geisslerbenjamin.jkmap.semicircle;

import de.geisslerbenjamin.jkmap.circle.CircleConfiguration;
import de.geisslerbenjamin.jkmap.semicircle.interfaces.SemiCircleConfigurationInterface;
import javafx.scene.paint.Paint;

/**
 * Semi-circle configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class SemiCircleConfiguration extends CircleConfiguration implements SemiCircleConfigurationInterface {
    /**
     * Constructor.
     *
     * @param id
     * @param x
     * @param y
     * @param rotation
     * @param color
     * @param information
     * @param diameter
     */
    public SemiCircleConfiguration(int id, double x, double y, double rotation, Paint color, String information, double diameter) {
        super(id, x, y, rotation, color, information, diameter);
    }
}
