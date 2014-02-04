package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.*;

/**
 * Created by Benjamin on 26.01.14.
 */
public class DrawableDisplayConfiguration implements DrawableDisplayConfigurationInterface, ListenerInterface {
    private static DrawableDisplayConfigurationInterface self;
    private double zoom;
    private boolean move;
    private boolean edit;
    private boolean info;

    protected DrawableDisplayConfiguration(double zoom, boolean move, boolean edit, boolean info, MediatorInterface mediator) {
        this.zoom = zoom;
        this.move = move;
        this.edit = edit;
        this.info = info;

        mediator
                .register("image.zoom.in", this, 1)
                .register("image.zoom.out", this, 1)
                .register("display.move", this, 1)
                .register("display.info", this, 1)
                .register("display.edit", this, 1)
        ;
    }

    public static DrawableDisplayConfigurationInterface get(double zoom, boolean move, boolean edit, boolean info, MediatorInterface mediator) {
        if (self == null) {
            self = new DrawableDisplayConfiguration(zoom, move, edit, info, mediator);
        }

        return self;
    }

    @Override
    public double getZoom() {
        return this.zoom;
    }

    @Override
    public boolean isMove() {
        return this.move;
    }

    @Override
    public boolean isEdit() {
        return this.edit;
    }

    @Override
    public boolean isInfo() {
        return this.info;
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        switch (name) {
            case "image.zoom.in":
            case "image.zoom.out":
                this.zoom = ((ZoomEventInterface) event).getZoomFactor();
                return true;
            case "display.move":
            case "display.edit":
            case "display.info":

                this.move = ((DisplayConfigurationEventInterface) event).isMove();
                this.edit = ((DisplayConfigurationEventInterface) event).isEdit();
                this.info = ((DisplayConfigurationEventInterface) event).isInfo();
                System.out.println("move " + this.move);
                return true;
        }

        return false;
    }
}
