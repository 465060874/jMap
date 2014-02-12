package de.geisslerbenjamin.jmap.configuration.interfaces;

/**
 * Image configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ImageInterface {
    /**
     * Get the path to the image file.
     *
     * @return
     */
    public String getPath();

    /**
     * Set the new path to the image file.
     *
     * @param path
     * @return
     */
    public ImageInterface setPath(String path);

    /**
     * The factor to calc the number of pixels on the image based on the size of an element in meters.
     *
     * @return
     */
    public double getFactor();

    /**
     * The meter-pixel-factor based on the size of the image display box.
     *
     * @return
     */
    public double getFactorForImageSize();

    /**
     * Define how many pixels on the image are 1 meter.
     *
     * @param factor
     * @return
     */
    public ImageInterface setFactor(double factor);

    /**
     * Get the width of the image box.
     *
     * @return
     */
    public int getWidth();

    /**
     * Set the width of the image box.
     *
     * @param width
     * @return
     */
    public ImageInterface setWidth(int width);

    /**
     * Get the height of the image box.
     *
     * @return
     */
    public int getHeight();

    /**
     * Set the height of the image box.
     *
     * @param height
     * @return
     */
    public ImageInterface setHeight(int height);
}
