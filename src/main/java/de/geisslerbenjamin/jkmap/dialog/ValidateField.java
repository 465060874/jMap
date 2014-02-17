package de.geisslerbenjamin.jkmap.dialog;

import java.io.File;

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
        return this.isInteger(input) || input.matches("^[0-9]+([.|,][0-9]+)?$");
    }

    public boolean isDoubleOrZero(String input) {
        return input.matches("^([1-9][0-9]*([.|,][0-9]+)|0|0.0|0,0)?$");
    }

    public boolean isFile(String name) {
        File file = new File(name);

        return file.exists();
    }
}
