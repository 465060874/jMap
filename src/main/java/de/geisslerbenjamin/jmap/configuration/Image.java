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
    private int width;
    private int height;

    public Image(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public ImageInterface setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
