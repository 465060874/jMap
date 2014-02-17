package de.geisslerbenjamin.jkmap.configuration;

import de.geisslerbenjamin.jkmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.DataSourceInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.ImageInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.LanguageInterface;

/**
 * Configuration object which contains the special configurations.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Configuration implements ConfigurationInterface {
    private ImageInterface image;
    private DataSourceInterface dataSource;
    private LanguageInterface language;

    /**
     * Constructor.
     *
     * @param image
     * @param dataSource
     * @param language
     */
    public Configuration(ImageInterface image, DataSourceInterface dataSource, LanguageInterface language) {
        this.image = image;
        this.dataSource = dataSource;
        this.language = language;
    }

    @Override
    public ImageInterface getImage() {
        return this.image;
    }

    @Override
    public DataSourceInterface getDataSource() {
        return dataSource;
    }

    @Override
    public LanguageInterface getLanguage() {
        return language;
    }
}
