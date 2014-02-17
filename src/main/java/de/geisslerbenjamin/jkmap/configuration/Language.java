package de.geisslerbenjamin.jkmap.configuration;

import de.geisslerbenjamin.jkmap.configuration.interfaces.LanguageInterface;

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
