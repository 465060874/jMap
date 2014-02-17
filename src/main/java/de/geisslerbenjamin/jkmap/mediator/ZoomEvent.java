package de.geisslerbenjamin.jkmap.mediator;

import de.geisslerbenjamin.jkmap.mediator.interfaces.ZoomEventInterface;

/**
 * Zoom Event.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class ZoomEvent implements ZoomEventInterface {
    private double zoom;

    public ZoomEvent(double zoom) {
        this.zoom = zoom;
    }

    @Override
    public double getZoomFactor() {
        return this.zoom;
    }
}
