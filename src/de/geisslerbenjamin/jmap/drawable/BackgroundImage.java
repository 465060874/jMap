package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.configuration.interfaces.ImageInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.BackgroundImageInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ZoomEventInterface;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

/**
 * Created by Benjamin on 23.01.14.
 */
public class BackgroundImage implements BackgroundImageInterface, ListenerInterface {
    private ImageInterface config;
    private MediatorInterface mediator;
    private ImageView imageView;
    private double zoom;


    public BackgroundImage(ImageInterface config, MediatorInterface mediator) {
        this.config = config;
        this.mediator = mediator;

        this.mediator
                .register("image.zoom.in", this)
                .register("image.zoom.out", this);
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
        }

        return false;
    }

    @Override
    public ScrollPane draw() {
        ScrollPane pane = new ScrollPane();

        if (new File(this.config.getPath()).exists() == true) {
            // create image
            System.out.println(this.config.getPath());
            javafx.scene.image.Image image = new javafx.scene.image.Image("file:" + this.config.getPath());
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
