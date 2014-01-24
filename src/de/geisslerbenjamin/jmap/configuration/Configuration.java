package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;

/**
 * Created by Benjamin on 23.01.14.
 */
public class Configuration implements ConfigurationInterface {
    private ImageInterface image;

    public Configuration(ImageInterface image) {
        this.image = image;
    }

    @Override
    public ImageInterface getImage() {
        return this.image;
    }
}
