package de.geisslerbenjamin.jmap.data;

import de.geisslerbenjamin.jmap.data.interfaces.RowParserInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
abstract public class ParseDrawable implements RowParserInterface {
    private DrawableDisplayConfigurationInterface displayConfig;
    private MediatorInterface mediator;

    public ParseDrawable(DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        this.displayConfig = displayConfig;
        this.mediator = mediator;
    }

    protected DrawableDisplayConfigurationInterface getDisplayConfig() {
        return displayConfig;
    }

    protected MediatorInterface getMediator() {
        return mediator;
    }

    /**
     * Transfers the color name into a color object.
     *
     * @param name
     * @return
     */
    protected Paint getColor(String name) {
        switch (name) {
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}
