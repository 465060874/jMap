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
        double width = ((RectangleConfigurationInterface) this.getConfig()).getWidth() * this.getDisplayConfig().getFactor() * this.getDisplayConfig().getZoom();
        double height = ((RectangleConfigurationInterface) this.getConfig()).getHeight() * this.getDisplayConfig().getFactor() * this.getDisplayConfig().getZoom();

        if (this.canvas == null) {
            this.canvas = new Canvas(width, height);

            // Rotation
            this.canvas.getTransforms().add(new Rotate(((RectangleConfigurationInterface) this.getConfig()).getRotation()));
            this.rotation = ((RectangleConfigurationInterface) this.getConfig()).getRotation();

            this.canvas.setLayoutX(this.getConfig().getX());
            this.canvas.setLayoutY(this.getConfig().getY());
        } else {
            this.canvas.setHeight(height);
            this.canvas.setWidth(width);
        }

        final GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);
        graphicsContext.setFill(this.getConfig().getColor());
        graphicsContext.fillRect(0, 0, width, height);

        // test if rotation has changed
        if (this.rotation != ((RectangleConfigurationInterface) this.getConfig()).getRotation()) {
            this.canvas.getTransforms().add(new Rotate(-1 * this.rotation));
            this.canvas.getTransforms().add(new Rotate(((RectangleConfigurationInterface) this.getConfig()).getRotation()));
            this.rotation = ((RectangleConfigurationInterface) this.getConfig()).getRotation();
        }
    }
}
