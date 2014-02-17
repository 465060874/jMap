package de.geisslerbenjamin.jkmap.drawable;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import javafx.scene.paint.Paint;

/**
 * Implementation of the basic drawable configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class DrawableConfiguration implements DrawableConfigurationInterface {
    private int id;
    private double x;
    private double y;
    private double rotation;
    private Paint color;
    private String information;

    /**
     * Constructor.
     *
     * @param id
     * @param x
     * @param y
     * @param rotation
     * @param color
     * @param information
     */
    public DrawableConfiguration(int id, double x, double y, double rotation, Paint color, String information) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.color = color;
        this.information = information;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public DrawableConfigurationInterface setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }

    @Override
    public DrawableConfigurationInterface setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }

    @Override
    public Paint getColor() {
        return color;
    }

    @Override
    public String getInformation() {
        return information;
    }

    @Override
    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
