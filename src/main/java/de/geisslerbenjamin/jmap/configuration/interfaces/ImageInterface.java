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

    public int getWidth();

    public int getHeight();
}
