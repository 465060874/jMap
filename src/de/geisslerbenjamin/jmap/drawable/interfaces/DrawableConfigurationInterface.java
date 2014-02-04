package de.geisslerbenjamin.jmap.drawable.interfaces;

import javafx.scene.paint.Paint;

/**
 * Basic configuration values of any drawable.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DrawableConfigurationInterface {
    /**
     * The id of the object.
     *
     * @return
     */
    public int getId();

    /**
     * Change the id of the object.
     *
     * @param id
     * @return
     */
    public DrawableConfigurationInterface setId(int id);

    /**
     * The x coordinate.
     *
     * @return
     */
    public double getX();

    /**
     * The y coordinate.
     *
     * @return
     */
    public double getY();

    /**
     * The color.
     *
     * @return
     */
    public Paint getColor();

    /**
     * The full information or description of the drawable.
     *
     * @return
     */
    public String getInformation();

    /**
     * Change the position of the drawable.
     *
     * @param x
     * @param y
     */
    public void move(double x, double y);
}
