package de.geisslerbenjamin.jkmap.data;

import de.geisslerbenjamin.jkmap.data.interfaces.RowParserInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Basic implementation of a row parser, which transfers the data values into drawable configuration values.
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

    /**
     * Access the overall drawable configuration.
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

    /**
     * Transfers the color name into a color object.
     *
     * @param name
     * @param transparency
     * @return
     */
    protected Paint getColor(String name, double transparency) {
        switch (name) {
            case "red":
                return Color.web("#FF0000", transparency);
            case "green":
                return Color.web("#008000", transparency);
            case "blue":
                return Color.web("#0000FF", transparency);
            case "yellow":
                return Color.web("#FFFF00", transparency);
            case "orange":
                return Color.web("#FFA500", transparency);
            case "violet":
                return Color.web("#EE82EE", transparency);
            case "pink":
                return Color.web("#FFC0CB", transparency);
            case "turquoise":
                return Color.web("#40E0D0", transparency);
            case "white":
                return Color.web("#FFFFFF", transparency);
            default:
                return Color.web("#000000", transparency);
        }
    }
}
