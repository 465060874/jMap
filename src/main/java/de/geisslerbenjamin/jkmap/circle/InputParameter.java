package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.dialog.interfaces.InputParameterInterface;

import java.util.ArrayList;

/**
 * Parameters of a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class InputParameter implements InputParameterInterface {
    public int id;
    public double diameter;
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
