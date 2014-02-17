package de.geisslerbenjamin.jkmap.mediator.interfaces;

/**
 * Simple listener interface.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ListenerInterface {
    /**
     * Execute a command listening to an event.
     *
     * @param name
     * @param event
     * @return
     */
    public boolean exec(String name, EventInterface event);
}
