package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.FactorInterface;
import javafx.scene.image.Image;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Factor implements FactorInterface {
    @Override
    public double calculate(double factor, double width, double height, String file) {
        javafx.scene.image.Image image = new Image("file:" + file);

        double originalSize;
        if (image.getHeight() > image.getWidth()) {
            originalSize = image.getHeight();
        } else {
            originalSize = image.getWidth();
        }

        double newSize;
        if (height > width) {
            newSize = height;
        } else {
            newSize = width;
        }

        return (((100 / originalSize) * newSize) / 100) * factor;
    }
}
