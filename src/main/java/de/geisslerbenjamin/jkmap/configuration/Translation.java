package de.geisslerbenjamin.jkmap.configuration;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Translation implements TranslationInterface {
    private Map<String, String> translations;

    public Translation(String file) throws Exception {
        this.translations = (Map) YamlToMap.read(file);
    }

    @Override
    public String translate(String token) {
        return this.translations.get(token);
    }

    @Override
    public String translate(ArrayList<String> tokens) {
        return this.translate(tokens, "\n");
    }

    @Override
    public String translate(ArrayList<String> tokens, String splitter) {
        StringBuilder builder = new StringBuilder();

        for (String token : tokens) {
            builder
                    .append(this.translations.get(token))
                    .append(splitter);
        }

        return builder.toString();
    }
}
