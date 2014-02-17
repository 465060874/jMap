package de.geisslerbenjamin.jkmap.semicircle;

import de.geisslerbenjamin.jkmap.circle.interfaces.CircleConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.AbstractDrawable;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Rotate;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class SemiCircle extends AbstractDrawable {
    private double rotation;

    public SemiCircle(DrawableConfigurationInterface config, DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        super(config, displayConfig, mediator);
    }

    @Override
    protected void initCanvas() {
        double diameter = ((CircleConfigurationInterface) this.getConfig()).getDiameter() * this.getDisplayConfig().getFactor() * this.getDisplayConfig().getZoom();

        if (this.canvas == null) {
            this.canvas = new Canvas(diameter, diameter);

            // Rotation
            this.canvas.getTransforms().add(new Rotate(this.getConfig().getRotation()));
            this.rotation = this.getConfig().getRotation();

            this.canvas.setLayoutX(this.getConfig().getX());
            this.canvas.setLayoutY(this.getConfig().getY());
        } else {
            this.canvas.setHeight(diameter);
            this.canvas.setWidth(diameter);
        }

        final GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, diameter, diameter);
        graphicsContext.setFill(this.getConfig().getColor());
        graphicsContext.fillArc(0, 0, diameter, diameter, 0, 180, ArcType.CHORD);

        // test if rotation has changed
        if (this.rotation != this.getConfig().getRotation()) {
            this.canvas.getTransforms().add(new Rotate(-1 * this.rotation));
            this.canvas.getTransforms().add(new Rotate(this.getConfig().getRotation()));
            this.rotation = this.getConfig().getRotation();
        }
    }
}
