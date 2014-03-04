package de.geisslerbenjamin.jkmap.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Transfer a yml-file into a Map object.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class YamlToMap {
    /**
     * Transfer a yaml file into a Map object.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static Map read(String file) throws Exception {
        try {
            String doc = readFile(file);
            return (Map) new Yaml().load(doc);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    /**
     * Read the content of the yml file.
     *
     * @param file
     * @return
     * @throws IOException
     */
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        reader.close();
        return stringBuilder.toString();
    }
}
