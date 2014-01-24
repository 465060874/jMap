package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.ImageInterface;
import javafx.scene.image.ImageView;

/**
 * Created by Benjamin on 23.01.14.
 */
public class Image implements ImageInterface {
    private de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface config;

    public Image(de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface config) {
        this.config = config;
    }

    @Override
    public ImageView draw() {
        javafx.scene.image.Image image = new javafx.scene.image.Image("file:" + this.config.getPath());
        ImageView imageView = new ImageView(image);

        double factor = image.getWidth() / this.config.getWidth();
        imageView.fitHeightProperty().setValue(image.getHeight() / factor);
        imageView.fitWidthProperty().setValue(this.config.getWidth());

        System.out.println("imag");
        return imageView;
    }
}
