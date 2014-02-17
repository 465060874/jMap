package de.geisslerbenjamin.jkmap.drawable;

import de.geisslerbenjamin.jkmap.configuration.interfaces.ImageInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.SaveInterface;
import de.geisslerbenjamin.jkmap.mediator.ZoomEvent;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Displays a dialog to save the current background image and the displayed elements to a *.png file.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Save implements SaveInterface {
    private Stage stage;
    private ScrollPane pane;
    private ImageInterface imageConfig;
    private MediatorInterface mediator;
    private String title;
    private Image backgroundPicture;

    /**
     * Constructor.
     *
     * @param stage
     * @param pane
     * @param imageConfig
     * @param mediator
     * @param title
     */
    public Save(Stage stage, ScrollPane pane, ImageInterface imageConfig, MediatorInterface mediator, String title) {
        this.stage = stage;
        this.pane = pane;
        this.imageConfig = imageConfig;
        this.mediator = mediator;
        this.title = title;
    }

    /**
     * Displays a dialog to save the current background image and the displayed elements to a *.png file.
     */
    public void dialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("png", "*.png")
        );

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            // add file extension
            if (!file.getAbsolutePath().matches("/.png$/")) {
                file = new File(file.getAbsolutePath() + ".png");
            }

            this.initPrint();
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(this.createImage(), null), "png", file);
            } catch (Exception error) {
                System.err.println(error.getMessage());
            }

            this.endPrint();
        }
    }

    /**
     * Reset the background image to its full size and re-draw the elements.
     */
    private void initPrint() {
        this.backgroundPicture = new Image("file:" + this.imageConfig.getPath());
        double zoom;

        if (this.backgroundPicture.getHeight() > this.backgroundPicture.getWidth()) {
            zoom = this.backgroundPicture.getHeight() / this.imageConfig.getHeight();
        } else {
            zoom = this.backgroundPicture.getWidth() / this.imageConfig.getWidth();
        }

        this.mediator.dispatch("image.print.start");
        this.mediator.dispatch("image.zoom.out", new ZoomEvent(zoom));
    }

    /**
     * Restore the changes made for saving.
     */
    private void endPrint() {
        this.mediator.dispatch("image.print.end");
        this.mediator.dispatch("configuration.changed");
        this.mediator.dispatch("image.zoom.out", new ZoomEvent(1.0));
    }

    /**
     * Store a snapshot of the currently displayed image and drawables into an image.
     *
     * @return
     */
    public WritableImage createImage() {
        Group imageBox = (Group) pane.getContent();
        WritableImage image = new WritableImage(
                (int) this.backgroundPicture.getWidth(),
                (int) this.backgroundPicture.getHeight()
        );

        imageBox.snapshot(null, image);
        return image;
    }

}
