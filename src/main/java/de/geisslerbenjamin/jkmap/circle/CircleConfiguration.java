package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.circle.interfaces.CircleConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.DrawableConfiguration;
import javafx.scene.paint.Paint;

/**
 * Configuration of a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class CircleConfiguration extends DrawableConfiguration implements CircleConfigurationInterface {
    private double diameter;

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
    public CircleConfiguration(int id, double x, double y, double rotation, Paint color, String information, double diameter) {
        super(id, x, y, rotation, color, information);
        this.diameter = diameter;
    }

    @Override
    public double getDiameter() {
        return this.diameter;
    }

    @Override
    public CircleConfigurationInterface setDiameter(double diameter) {
        this.diameter = diameter;
        return this;
    }
}
