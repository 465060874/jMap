package de.geisslerbenjamin.jmap.mediator.interfaces;

/**
 * Display of an information event.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface InformationEventInterface extends ElementEventInterface {
    /**
     * The information to be displayed.
     *
     * @return
     */
    public String getInformation();
}
