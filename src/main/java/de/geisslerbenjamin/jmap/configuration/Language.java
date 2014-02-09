package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.LanguageInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Language implements LanguageInterface {
    private String language;

    public Language(String language) {
        this.language = language;
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
