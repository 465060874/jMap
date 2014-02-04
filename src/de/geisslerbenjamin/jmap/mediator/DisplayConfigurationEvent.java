package de.geisslerbenjamin.jmap.mediator;

import de.geisslerbenjamin.jmap.mediator.interfaces.DisplayConfigurationEventInterface;

/**
 * Event for changes on the drawable handling.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class DisplayConfigurationEvent implements DisplayConfigurationEventInterface {
    private boolean move;
    private boolean edit;
    private boolean info;

    public DisplayConfigurationEvent(boolean move, boolean edit, boolean info) {
        this.move = move;
        this.edit = edit;
        this.info = info;
    }

    @Override
    public boolean isMove() {
        return move;
    }

    @Override
    public boolean isEdit() {
        return edit;
    }

    @Override
    public boolean isInfo() {
        return info;
    }
}
