package de.geisslerbenjamin.jkmap.drawable.interfaces;

/**
 * Configuration for the display options.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DrawableDisplayConfigurationInterface {
    public double getZoom();

    /**
     * Get the factor to transfer 1 m into a number of pixels.
     *
     * @return
     */
    public double getFactor();

    public boolean isMove();

    public boolean isInfo();

    public boolean isEdit();
}
