package de.geisslerbenjamin.jmap.rectangle;

import java.util.ArrayList;

/**
 * All parameters of an rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class InputParameter {
    public int id;
    public double width;
    public double height;
    public double rotation;
    public boolean valid;
    public ArrayList<String> message;

    /**
     * Constructor.
     */
    public InputParameter() {
        this.message = new ArrayList<>();
    }
}
