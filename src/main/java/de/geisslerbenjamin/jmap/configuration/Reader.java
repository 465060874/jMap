package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.DataSourceInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.LanguageInterface;

import java.util.Map;

/**
 * Parse the yml configuration file and create a configuration object out of the data.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Reader {

    /**
     * Parse the yml configuration file and create a configuration object out of the data.
     *
     * @param file
     * @return
     */
    public static ConfigurationInterface read(String file) throws Exception {

        Map map = YamlToMap.read(file);

        return new Configuration(
                loadImage((Map) map.get("image")),
                loadDataSource((Map) map.get("dataSource")),
                loadLanguage((Map) map.get("language"))
        );
    }

    /**
     * Create a configuration object for the background image.
     *
     * @param config
     * @return
     */
    private static ImageInterface loadImage(Map config) {
        return new Image(
                (String) config.get("path"),
                Double.parseDouble(config.get("factor").toString()),
                Integer.parseInt(config.get("width").toString()),
                Integer.parseInt(config.get("height").toString())
        );
    }

    /**
     * Create a configuration object for the data access.
     *
     * @param config
     * @return
     */
    private static DataSourceInterface loadDataSource(Map config) {
        return new DataSource(
                config.get("file").toString(),
                config.get("table").toString()
        );
    }

    /**
     * Create a configuration object for the language configuration.
     *
     * @param config
     * @return
     */
    private static LanguageInterface loadLanguage(Map config) {
        return new Language(config.get("country").toString());
    }
}
