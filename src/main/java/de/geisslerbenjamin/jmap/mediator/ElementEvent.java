package de.geisslerbenjamin.jmap.mediator;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ElementEventInterface;

/**
 * A event raised for a element in the database.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class ElementEvent implements ElementEventInterface {
    private int id;
    private DrawableConfigurationInterface configuration;

    public ElementEvent(int id, DrawableConfigurationInterface configuration) {
        this.id = id;
        this.configuration = configuration;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public DrawableConfigurationInterface getConfiguration() {
        return configuration;
    }
}
