package de.geisslerbenjamin.jkmap.mediator;

import de.geisslerbenjamin.jkmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.ConfigurationEventInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class ConfigurationEvent implements ConfigurationEventInterface {
    private ConfigurationInterface configuration;

    /**
     * Constructor.
     *
     * @param configuration
     */
    public ConfigurationEvent(ConfigurationInterface configuration) {
        this.configuration = configuration;
    }

    @Override
    public ConfigurationInterface getConfiguration() {
        return configuration;
    }
}
