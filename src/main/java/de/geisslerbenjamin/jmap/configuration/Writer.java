package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.*;
import de.geisslerbenjamin.jmap.mediator.ConfigurationEvent;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Write the configuration to the configuration file.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Writer implements WriterInterface {
    private MediatorInterface mediator;
    private String file;

    /**
     * Constructor.
     *
     * @param mediator
     * @param file
     */
    public Writer(MediatorInterface mediator, String file) {
        this.mediator = mediator;
        this.file = file;
    }

    /**
     * Write the configuration to the configuration file on the disk.
     *
     * @param configuration actual configuration
     * @return
     */
    public boolean updateConfiguration(ConfigurationInterface configuration) {
        Map<String, Object> map = new HashMap<>();
        map.put("image", this.parseImage(configuration.getImage()));
        map.put("dataSource", this.parseDataSource(configuration.getDataSource()));
        map.put("language", this.parseLanguage(configuration.getLanguage()));

        if (this.writeToDisk(map)) {
            return this.mediator.dispatch("configuration.changed", new ConfigurationEvent(configuration));
        }

        return false;
    }

    /**
     * Transfer the image configuration entry into savable yaml objects.
     *
     * @param image
     * @return
     */
    private Map<String, Object> parseImage(ImageInterface image) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", image.getPath());
        map.put("factor", image.getFactor());
        map.put("width", image.getWidth());
        map.put("height", image.getHeight());

        return map;
    }

    /**
     * Transfer the data source configuration entry into savable yaml objects.
     *
     * @param dataSource
     * @return
     */
    private Map<String, Object> parseDataSource(DataSourceInterface dataSource) {
        Map<String, Object> map = new HashMap<>();
        map.put("file", dataSource.getFile());
        map.put("table", dataSource.getTable());

        return map;
    }

    /**
     * Parse the language properties.
     *
     * @param language
     * @return
     */
    private Map<String, Object> parseLanguage(LanguageInterface language) {
        Map<String, Object> map = new HashMap<>();
        map.put("country", language.getLanguage());

        return map;
    }

    /**
     * Write yaml configuration to the disk.
     *
     * @param map
     * @return
     */
    private boolean writeToDisk(Map<String, Object> map) {
        Yaml yml = new Yaml();

        FileWriter writer = null;
        try {
            writer = new FileWriter(this.file);
            writer.write(yml.dump(map));

        } catch (IOException e) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }

        return true;
    }
}
