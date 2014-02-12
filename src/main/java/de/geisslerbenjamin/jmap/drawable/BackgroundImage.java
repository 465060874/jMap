package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.BackgroundImageInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ZoomEventInterface;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

/**
 * Draw the background image.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class BackgroundImage implements BackgroundImageInterface, ListenerInterface {
    private ImageInterface config;
    private MediatorInterface mediator;
    private ImageView imageView;
    private double zoom;

    /**
     * Constructor.
     *
     * @param config
     * @param mediator
     */
    public BackgroundImage(ImageInterface config, MediatorInterface mediator) {
        this.config = config;
        this.mediator = mediator;

        this.mediator
                .register("image.zoom.in", this)
                .register("image.zoom.out", this)
                .register("image.print.start", this)
                .register("image.print.end", this)
        ;
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        switch (name) {
            case "image.zoom.in":
            case "image.zoom.out":
                this.zoom = ((ZoomEventInterface) event).getZoomFactor();
                this.imageView.setFitWidth(this.zoom * this.config.getWidth());
                this.imageView.setFitHeight(this.zoom * this.config.getHeight());
                return true;
            case "image.print.start":
                Image image = new Image("file:" + this.config.getPath());
                this.imageView.setFitWidth(image.getWidth());
                this.imageView.setFitHeight(image.getHeight());
                return true;
            case "image.print.end":
                this.imageView.setFitWidth(this.zoom * this.config.getWidth());
                this.imageView.setFitHeight(this.zoom * this.config.getHeight());
                return true;
        }

        return false;
    }

    @Override
    public ScrollPane draw() {
        ScrollPane pane = new ScrollPane();

        if (new File(this.config.getPath()).exists()) {
            // create image
            Image image = new Image("file:" + this.config.getPath());
            this.imageView = new ImageView(image);
            this.imageView.preserveRatioProperty().setValue(true);
            this.imageView.setFitWidth(this.config.getWidth());
            this.imageView.setFitHeight(this.config.getHeight());

            // create scrollable pane
            pane.setMinHeight(this.config.getHeight());
            pane.setMinWidth(this.config.getWidth());

            // create group to display elements in it
            Group box = new Group();
            box.getChildren().add(this.imageView);
            pane.setContent(box);
        } else {
            pane.setContent(new Text("Missing image!"));
        }

        return pane;
    }
}
