package de.geisslerbenjamin.jmap.configuration.interfaces;

/**
 * Configuration object which contains the special configurations.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ConfigurationInterface {
    /**
     * Access the image configuration.
     *
     * @return
     */
    public ImageInterface getImage();

    /**
     * Access the data source configuration.
     *
     * @return
     */
    public DataSourceInterface getDataSource();

    /**
     * Access the language configuration.
     *
     * @return
     */
    public LanguageInterface getLanguage();
}
