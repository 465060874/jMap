package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.drawable.DrawableConfiguration;
import de.geisslerbenjamin.jmap.rectangle.interfaces.RectangleConfigurationInterface;
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
    private double rotation;

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
    public Configuration(int id, double x, double y, Paint color, String information, double width, double height, double rotation) {
        super(id, x, y, color, information);

        this.width = width;
        this.height = height;
        this.rotation = rotation;
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

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public RectangleConfigurationInterface setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }
}
