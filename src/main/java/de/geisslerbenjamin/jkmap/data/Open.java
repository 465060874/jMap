package de.geisslerbenjamin.jkmap.data;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Open Microsoft Access with the database.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Open {
    /**
     * Open Microsoft Access with the database.
     *
     * @param file
     */
    public void open(String file) {
        try {
            Desktop.getDesktop().open(new File(file));
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }
}
