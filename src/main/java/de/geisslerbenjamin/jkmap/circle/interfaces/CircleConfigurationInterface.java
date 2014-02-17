package de.geisslerbenjamin.jkmap.circle.interfaces;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;

/**
 * Configuration of a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface CircleConfigurationInterface extends DrawableConfigurationInterface {
    /**
     * Access the diameter.
     *
     * @return
     */
    public double getDiameter();

    /**
     * Change the diameter.
     *
     * @param diameter
     * @return
     */
    public CircleConfigurationInterface setDiameter(double diameter);
}
