package de.geisslerbenjamin.jmap.dialog;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class ValidateField {
    public boolean isInteger(String input) {
        return input.matches("^[1-9][0-9]*$");
    }

    public boolean isIntegerOrZero(String input) {
        return input.matches("^[1-9][0-9]*$|^0$|^0[,|.]0$");
    }

    public boolean isDouble(String input) {
        return input.matches("^[1-9][0-9]*([.|,][0-9]+)?$");
    }

    public boolean isDoubleOrZero(String input) {
        return input.matches("^([1-9][0-9]*([.|,][0-9]+)|0|0.0|0,0)?$");
    }
}
