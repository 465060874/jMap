package de.geisslerbenjamin.jkmap.configuration.interfaces;

/**
 * Write the configuration to the configuration file.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface WriterInterface {
    /**
     * Write the configuration to the configuration file.
     *
     * @param configuration actual configuration
     * @return success or not
     */
    public boolean updateConfiguration(ConfigurationInterface configuration);
}
