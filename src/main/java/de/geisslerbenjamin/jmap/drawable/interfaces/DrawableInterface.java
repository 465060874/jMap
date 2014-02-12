package de.geisslerbenjamin.jmap.drawable.interfaces;

import javafx.scene.canvas.Canvas;

/**
 * Drawable element.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DrawableInterface {
    /**
     * Access the id of the element.
     *
     * @return id
     */
    public int getId();

    /**
     * Draw the element and return its canvas object.
     *
     * @return
     */
    public Canvas draw();
}
