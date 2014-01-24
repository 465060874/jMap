/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Parse the yml configuration file and create a configuration object out of the data.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 */
public class Reader {

    /**
     * Parse the yml configuration file and create a configuration object out of the data.
     *
     * @param file
     * @return
     */
    public static ConfigurationInterface read(String file) throws Exception {
        try {
            String doc = readFile(file);
            Map map = (Map) new Yaml().load(doc);

            return new Configuration(loadImageConfig((Map) map.get("image")));
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        reader.close();
        return stringBuilder.toString();
    }

    /**
     * Create a configuration object for the background image.
     * @param config
     * @return
     */
    private static ImageInterface loadImageConfig(Map config) {
        return new Image(
                (String) config.get("path"),
                Integer.parseInt(config.get("width").toString()),
                Integer.parseInt(config.get("height").toString())
        );
    }
}
