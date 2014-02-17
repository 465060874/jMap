package de.geisslerbenjamin.jkmap.data.interfaces;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;

/**
 * Create a new row in the access database of the given element type.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface RowCreatorInterface {
    /**
     * Create a new row in the access database of the given element type.
     *
     * @param type
     * @param config
     */
    public void create(String type, DrawableConfigurationInterface config);
}
