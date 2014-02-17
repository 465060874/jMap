package de.geisslerbenjamin.jkmap.mediator.interfaces;

import de.geisslerbenjamin.jkmap.configuration.interfaces.ConfigurationInterface;

/**
 * Get the changed configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ConfigurationEventInterface extends EventInterface {
    /**
     * Get the changed configuration.
     *
     * @return
     */
    public ConfigurationInterface getConfiguration();
}
