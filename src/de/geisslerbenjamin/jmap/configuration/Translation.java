package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;

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
        this.translations = YamlToMap.read(file);
    }

    public String translate(String token) {
        return this.translations.get(token);
    }
}
