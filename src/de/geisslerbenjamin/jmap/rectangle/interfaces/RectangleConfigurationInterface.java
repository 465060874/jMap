package de.geisslerbenjamin.jmap.rectangle.interfaces;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;

/**
 * Additional configuration values for rectangles.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface RectangleConfigurationInterface extends DrawableConfigurationInterface {
    /**
     * The width size of the rectangle in pixel.
     *
     * @return
     */
    public double getWidth();

    /**
     * Change the width.
     *
     * @param width
     * @return
     */
    public RectangleConfigurationInterface setWidth(double width);

    /**
     * The height size of the rectangle in pixel.
     *
     * @return
     */
    public double getHeight();

    /**
     * Change the height of the rectangle in pixel.
     *
     * @param height
     * @return
     */
    public RectangleConfigurationInterface setHeight(double height);

    /**
     * Get the rotation of the rectangle.
     *
     * @return
     */
    public double getRotation();

    /**
     * Change the rotation of the rectangle.
     *
     * @param rotation
     * @return
     */
    public RectangleConfigurationInterface setRotation(double rotation);
}
