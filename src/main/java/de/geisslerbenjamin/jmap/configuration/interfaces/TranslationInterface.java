package de.geisslerbenjamin.jmap.configuration.interfaces;

import java.util.ArrayList;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface TranslationInterface {
    /**
     * Translates a single message.
     *
     * @param token
     * @return
     */
    public String translate(String token);

    /**
     * Translates multiple messages an combines them.
     *
     * @param tokens
     * @return
     */
    public String translate(ArrayList<String> tokens);

    /**
     * Translates multiple messages an combines them.
     *
     * @param tokens
     * @param splitter
     * @return
     */
    public String translate(ArrayList<String> tokens, String splitter);
}
