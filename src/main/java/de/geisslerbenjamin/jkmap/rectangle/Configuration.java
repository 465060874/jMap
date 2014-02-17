package de.geisslerbenjamin.jkmap.rectangle;

import de.geisslerbenjamin.jkmap.drawable.DrawableConfiguration;
import de.geisslerbenjamin.jkmap.rectangle.interfaces.RectangleConfigurationInterface;
import javafx.scene.paint.Paint;

/**
 * Configuration class for a drawable rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Configuration extends DrawableConfiguration implements RectangleConfigurationInterface {
    private double width;
    private double height;

    /**
     * Constructor.
     *
     * @param id
     * @param x
     * @param y
     * @param color
     * @param information
     * @param width
     * @param height
     * @param rotation
     */
    public Configuration(int id, double x, double y, double rotation, Paint color, String information, double width, double height) {
        super(id, x, y, rotation, color, information);

        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public RectangleConfigurationInterface setWidth(double width) {
        this.width = width;
        return this;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public RectangleConfigurationInterface setHeight(double height) {
        this.height = height;
        return this;
    }
}
