package de.geisslerbenjamin.jkmap.dialog.interfaces;

import java.util.ArrayList;

/**
 * Basic parameter container to edit or create new drawables.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface InputParameterInterface {
    /**
     * Test if the input parameter are valid.
     *
     * @return
     */
    public boolean isValid();

    /**
     * Get the error messages.
     *
     * @return
     */
    public ArrayList<String> getMessage();
}
