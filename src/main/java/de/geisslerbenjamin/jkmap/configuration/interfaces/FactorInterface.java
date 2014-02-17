package de.geisslerbenjamin.jkmap.configuration.interfaces;

/**
 * Calculate the factor to transfer metric values into pixel based on the image-box size and the given
 * meter-to-pixel-factor of the background image.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface FactorInterface {
    /**
     * Calculate the factor to transfer metric values into pixel based on the image-box size and the given
     * meter-to-pixel-factor of the background image.
     *
     * @param factor
     * @param width
     * @param height
     * @param image
     * @return
     */
    public double calculate(double factor, double width, double height, String image);
}
