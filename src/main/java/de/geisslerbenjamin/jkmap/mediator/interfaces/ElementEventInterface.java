package de.geisslerbenjamin.jkmap.mediator.interfaces;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;

/**
 * Simple element event.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ElementEventInterface extends EventInterface {
    /**
     * Id of the element in the database.
     *
     * @return
     */
    public int getId();

    public DrawableConfigurationInterface getConfiguration();
}
