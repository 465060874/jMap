package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;

/**
 * Configuration values for the background image.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Image implements ImageInterface {
    private String path;
    private double factor;
    private int width;
    private int height;
    private double factorForImageSize;

    /**
     * Constructor.
     *
     * @param path
     * @param factor
     * @param width
     * @param height
     */
    public Image(String path, double factor, int width, int height) {
        this.path = path;
        this.factor = factor;
        this.width = width;
        this.height = height;
        this.calcFactorForImage();
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public ImageInterface setPath(String path) {
        this.path = path;
        this.calcFactorForImage();
        return this;
    }

    @Override
    public double getFactor() {
        return factor;
    }

    @Override
    public ImageInterface setFactor(double factor) {
        this.factor = factor;
        this.calcFactorForImage();
        return this;
    }

    @Override
    public double getFactorForImageSize() {
        return this.factorForImageSize;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public ImageInterface setWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public ImageInterface setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Recalculate the scaling factor (1 meter = X pixel) for the size of the displayed image in the image box.
     */
    private void calcFactorForImage() {
        this.factorForImageSize = new Factor().calculate(this.factor, this.width, this.height, this.path);
    }
}
