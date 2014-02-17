package de.geisslerbenjamin.jkmap.mediator;

import de.geisslerbenjamin.jkmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Simple mediator implementation.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Mediator implements MediatorInterface {
    private Map<String, Map<Integer, ArrayList<ListenerInterface>>> listeners;

    /**
     * Constructor.
     */
    public Mediator() {
        this.listeners = new HashMap<>();
    }

    @Override
    public MediatorInterface register(String name, ListenerInterface listener) {
        return this.register(name, listener, 1000);
    }

    @Override
    public MediatorInterface register(String name, ListenerInterface listener, int priority) {
        if (this.listeners.containsKey(name) == false) {
            this.listeners.put(name, new TreeMap<Integer, ArrayList<ListenerInterface>>());
        }

        if (!this.listeners.get(name).containsKey(priority)) {
            this.listeners.get(name).put(priority, new ArrayList<ListenerInterface>());
        }

        this.listeners.get(name).get(priority).add(listener);

        return this;
    }

    @Override
    public boolean dispatch(String name) {
        return this.dispatch(name, new EmptyEvent());
    }

    @Override
    public boolean dispatch(String name, EventInterface event) {
        if (this.listeners.containsKey(name)) {
            TreeMap<Integer, ArrayList<ListenerInterface>> reverseOrder = (TreeMap<Integer, ArrayList<ListenerInterface>>) this.listeners.get(name);

            for (Map.Entry<Integer, ArrayList<ListenerInterface>> entry : reverseOrder.entrySet()) {
                for (ListenerInterface listener : entry.getValue()) {
                    if (!listener.exec(name, event)) {
                        System.err.println("Error while executing the listener '" + listener.getClass() + "' for the event '" + name + "'");
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }
}
