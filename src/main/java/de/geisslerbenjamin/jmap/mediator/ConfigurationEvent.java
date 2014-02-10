package de.geisslerbenjamin.jmap.mediator;

import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ConfigurationEventInterface;

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
