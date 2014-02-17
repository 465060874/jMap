package de.geisslerbenjamin.jkmap.rectangle.interfaces;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;

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
}
