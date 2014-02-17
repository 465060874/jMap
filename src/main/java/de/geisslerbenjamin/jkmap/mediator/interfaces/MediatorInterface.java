package de.geisslerbenjamin.jkmap.mediator.interfaces;

/**
 * Simple mediator implementation.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface MediatorInterface {
    /**
     * Register a listener for an event.
     *
     * @param name
     * @param listener
     * @return
     */
    public MediatorInterface register(String name, ListenerInterface listener);

    /**
     * Register a listener with a given priority for an event.
     *
     * @param name
     * @param listener
     * @param priority
     * @return
     */
    public MediatorInterface register(String name, ListenerInterface listener, int priority);

    /**
     * Notify all listeners listing to the given event and inject an empty event object.
     *
     * @param name
     * @return
     */
    public boolean dispatch(String name);

    /**
     * Notify all listeners listening to the given event.
     *
     * @param name
     * @param event
     * @return
     */
    public boolean dispatch(String name, EventInterface event);
}
