package de.geisslerbenjamin.jmap.mediator.interfaces;

/**
 * Event for changes on the drawable handling.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DisplayConfigurationEventInterface extends EventInterface {
    /**
     * Get if drawables should be moved around.
     *
     * @return
     */
    public boolean isMove();

    /**
     * Get if the information should be displayed onclick.
     *
     * @return
     */
    public boolean isInfo();

    /**
     * Get if the drawables should be edited onclick
     *
     * @return
     */
    public boolean isEdit();
}
