package de.geisslerbenjamin.jkmap.mediator.interfaces;

/**
 * Zoom Event.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ZoomEventInterface extends EventInterface {
    /**
     * Zoom factor.
     *
     * @return actual zoom factor
     */
    public double getZoomFactor();
}
