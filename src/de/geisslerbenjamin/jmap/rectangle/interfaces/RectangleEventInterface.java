package de.geisslerbenjamin.jmap.rectangle.interfaces;

import de.geisslerbenjamin.jmap.mediator.interfaces.ElementEventInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface RectangleEventInterface extends ElementEventInterface {
    public double getWidth();

    public double getHeight();

    public double getRotation();
}
