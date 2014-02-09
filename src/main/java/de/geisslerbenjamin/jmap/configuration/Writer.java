package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.*;
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

    public Writer(MediatorInterface mediator, String file) {
        this.mediator = mediator;
        this.file = file;
    }

    public boolean updateConfiguration(ConfigurationInterface configuration) {
        Map<String, Object> map = new HashMap<>();
        map.put("image", this.parseImage(configuration.getImage()));
        map.put("dataSource", this.parseDataSource(configuration.getDataSource()));
        map.put("language", this.parseLanguage(configuration.getLanguage()));

        if (this.writeToDisk(map)) {
            return this.mediator.dispatch("configuration.changed");
        }

        return false;
    }

    private Map<String, Object> parseImage(ImageInterface image) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", image.getPath());
        map.put("width", image.getWidth());
        map.put("height", image.getHeight());

        return map;
    }

    private Map<String, Object> parseDataSource(DataSourceInterface dataSource) {
        Map<String, Object> map = new HashMap<>();
        map.put("file", dataSource.getFile());
        map.put("table", dataSource.getTable());

        return map;
    }

    private Map<String, Object> parseLanguage(LanguageInterface language) {
        Map<String, Object> map = new HashMap<>();
        map.put("country", language.getLanguage());

        return map;
    }

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
