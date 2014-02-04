package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.drawable.AbstractDrawable;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jmap.rectangle.interfaces.RectangleConfigurationInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

/**
 * An implementation of a simple rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Rectangle extends AbstractDrawable {
    private double rotation;

    public Rectangle(DrawableConfigurationInterface config, DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        super(config, displayConfig, mediator);
    }

    @Override
    protected void initCanvas() {
        if (this.canvas == null) {
            this.canvas = new Canvas(
                    ((RectangleConfigurationInterface) this.getConfig()).getWidth() * this.getDisplayConfig().getZoom(),
                    ((RectangleConfigurationInterface) this.getConfig()).getHeight() * this.getDisplayConfig().getZoom()
            );

            // Rotation
            this.canvas.getTransforms().add(new Rotate(((RectangleConfigurationInterface) this.getConfig()).getRotation()));
            this.rotation = ((RectangleConfigurationInterface) this.getConfig()).getRotation();

            this.canvas.setLayoutX(this.getConfig().getX());
            this.canvas.setLayoutY(this.getConfig().getY());
        } else {
            this.canvas.setHeight(((RectangleConfigurationInterface) this.getConfig()).getHeight() * this.getDisplayConfig().getZoom());
            this.canvas.setWidth(((RectangleConfigurationInterface) this.getConfig()).getWidth() * this.getDisplayConfig().getZoom());
        }

        final GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth() * this.getDisplayConfig().getZoom(), canvas.getHeight() * this.getDisplayConfig().getZoom());
        graphicsContext.setFill(this.getConfig().getColor());
        graphicsContext.fillRect(0, 0, canvas.getWidth() * this.getDisplayConfig().getZoom(), canvas.getHeight() * this.getDisplayConfig().getZoom());

        // test if rotation has changed
        if (this.rotation != ((RectangleConfigurationInterface) this.getConfig()).getRotation()) {
            this.canvas.getTransforms().add(new Rotate(-1 * this.rotation));
            this.canvas.getTransforms().add(new Rotate(((RectangleConfigurationInterface) this.getConfig()).getRotation()));
            this.rotation = ((RectangleConfigurationInterface) this.getConfig()).getRotation();
        }
    }
}
