package de.geisslerbenjamin.jmap.drawable.interfaces;

/**
 * Event for changes in the drawable handling.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DrawableDisplayConfigurationInterface {
    public double getZoom();

    public boolean isMove();

    public boolean isInfo();

    public boolean isEdit();
}
