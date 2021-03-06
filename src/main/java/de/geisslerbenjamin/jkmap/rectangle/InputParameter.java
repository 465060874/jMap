package de.geisslerbenjamin.jkmap.rectangle;

import de.geisslerbenjamin.jkmap.dialog.interfaces.InputParameterInterface;

import java.util.ArrayList;

/**
 * All parameters of an rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class InputParameter implements InputParameterInterface {
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


    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public ArrayList<String> getMessage() {
        return this.message;
    }
}
