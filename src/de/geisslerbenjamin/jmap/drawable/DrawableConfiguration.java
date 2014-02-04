package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
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
    private Paint color;
    private String information;

    public DrawableConfiguration(int id, double x, double y, Paint color, String information) {
        this.id = id;
        this.x = x;
        this.y = y;
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
