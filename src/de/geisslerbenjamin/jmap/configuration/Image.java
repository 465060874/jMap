package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;

/**
 * Created by Benjamin on 23.01.14.
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
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
