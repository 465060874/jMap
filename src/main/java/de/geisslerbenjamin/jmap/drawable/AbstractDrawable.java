package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jmap.mediator.ElementEvent;
import de.geisslerbenjamin.jmap.mediator.interfaces.ElementEventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 * Basic implementation of a drawable element.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
abstract public class AbstractDrawable implements DrawableInterface, ListenerInterface {
    private DrawableConfigurationInterface config;
    private DrawableDisplayConfigurationInterface displayConfig;
    private MediatorInterface mediator;
    protected Canvas canvas;


    protected AbstractDrawable(DrawableConfigurationInterface config, DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        this.config = config;
        this.mediator = mediator;
        this.displayConfig = displayConfig;

        this.mediator
                .register("image.zoom.in", this)
                .register("image.zoom.out", this)
                .register("display.move", this)
                .register("display.info", this)
                .register("display.edit", this)
                .register("element.edited", this)
        ;
    }

    @Override
    public int getId() {
        return this.config.getId();
    }

    /**
     * Access the configuration of the specific drawable.
     *
     * @return
     */
    protected DrawableConfigurationInterface getConfig() {
        return config;
    }

    /**
     * Access the configuration of all drawables.
     *
     * @return
     */
    protected DrawableDisplayConfigurationInterface getDisplayConfig() {
        return displayConfig;
    }

    /**
     * Access the mediator.
     *
     * @return
     */
    protected MediatorInterface getMediator() {
        return mediator;
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        switch (name) {
            case "image.zoom.in":
            case "image.zoom.out":
                return this.zoom();
            case "display.move":
            case "display.info":
            case "display.edit":
                this.draw();
                return true;
            case "element.edited":
                if (((ElementEventInterface) event).getId() == this.getId()) {
                    this.draw();
                }
                return true;
        }

        return false;
    }

    @Override
    public Canvas draw() {
        this.initCanvas();

        // position
        this.relocate();

        if (this.getDisplayConfig().isMove()) {
            // let the element be moved around
            final Position movedBy = new Position();
            this.canvas.setOnMouseClicked(null);
            this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    // x, y
                    movedBy.x = canvas.getLayoutX() - t.getSceneX();
                    movedBy.y = canvas.getLayoutY() - t.getSceneY();
                    canvas.setCursor(Cursor.HAND);
                }
            });

            this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    canvas.setLayoutX(t.getSceneX() + movedBy.x);
                    canvas.setLayoutY(t.getSceneY() + movedBy.y);

                    move(canvas.getLayoutX(), canvas.getLayoutY());
                }
            });
        } else if (this.getDisplayConfig().isEdit()) {
            this.canvas.setOnMousePressed(null);
            this.canvas.setOnMouseDragged(null);
            this.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getMediator().dispatch("element.edit", new ElementEvent(getId(), getConfig()));
                }
            });

        } else if (this.getDisplayConfig().isInfo()) {
            this.canvas.setOnMousePressed(null);
            this.canvas.setOnMouseDragged(null);
            this.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getMediator().dispatch("element.info", new ElementEvent(getId(), getConfig()));
                }
            });
        }

        return this.canvas;
    }

    /**
     * Init the implementation of a special canvas object.
     */
    abstract protected void initCanvas();

    /**
     * Redraw and relocate the element with the actual zoom factor.
     *
     * @return
     */
    protected boolean zoom() {
        this.draw();
        this.relocate();
        return true;
    }

    /**
     * Store the changed position in the element configuration.
     *
     * @param x
     * @param y
     */
    protected void move(double x, double y) {
        // System.out.println("moved id" + this.id + " to x:" + event.getX() + " y: " + event.getY());
        this.getConfig().move(
                x / this.getDisplayConfig().getZoom(),
                y / this.getDisplayConfig().getZoom()
        );

        getMediator().dispatch("element.moved", new ElementEvent(getId(), getConfig()));
    }

    /**
     * Calculate the position of the drawable depending on the zoom factor.
     */
    private void relocate() {
        this.canvas.relocate(
                this.getConfig().getX() * this.getDisplayConfig().getZoom(),
                this.getConfig().getY() * this.getDisplayConfig().getZoom()
        );
    }
}
