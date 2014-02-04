package de.geisslerbenjamin.jmap.rectangle;

import com.healthmarketscience.jackcess.Row;
import de.geisslerbenjamin.jmap.data.AccessRow;
import de.geisslerbenjamin.jmap.data.ParseDrawable;
import de.geisslerbenjamin.jmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jmap.rectangle.interfaces.RectangleConfigurationInterface;

/**
 * Transfers the data of an Microsoft Access AccessRow into a rectangle configuration object and the other way around.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class RowParser extends ParseDrawable {
    public RowParser(DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        super(displayConfig, mediator);
    }

    @Override
    public DrawableInterface read(Row row) {
        return new Rectangle(
                new Configuration(
                        Integer.parseInt(row.get("id").toString()),
                        Double.parseDouble(row.get("x").toString()),
                        Double.parseDouble(row.get("y").toString()),
                        this.getColor(row.get("color").toString()),
                        row.get("information").toString(),
                        Double.parseDouble(row.get("width").toString()),
                        Double.parseDouble(row.get("height").toString()),
                        Double.parseDouble(row.get("rotation").toString())

                ),
                this.getDisplayConfig(),
                this.getMediator()
        );
    }

    @Override
    public DrawableInterface read(AccessRowInterface row) {
        return new Rectangle(
                new Configuration(
                        row.getId(),
                        row.getX(),
                        row.getY(),
                        this.getColor(row.getColor()),
                        "",
                        row.getWidth(),
                        row.getHeight(),
                        row.getRotation()
                ),
                this.getDisplayConfig(),
                this.getMediator()
        );
    }

    @Override
    public AccessRowInterface create(DrawableConfigurationInterface config) {
        return new AccessRow()
                .setX(config.getX())
                .setY(config.getY())
                .setColor(config.getColor())
                .setType("rectangle")
                .setWidth(((RectangleConfigurationInterface) config).getWidth())
                .setHeight(((RectangleConfigurationInterface) config).getHeight())
                .setRotation(((RectangleConfigurationInterface) config).getRotation());
    }

    @Override
    public Row update(Row row, DrawableConfigurationInterface config) {
        row.put("id", config.getId());
        row.put("x", config.getX());
        row.put("y", config.getY());
        row.put("width", ((RectangleConfigurationInterface) config).getWidth());
        row.put("height", ((RectangleConfigurationInterface) config).getHeight());
        row.put("rotation", ((RectangleConfigurationInterface) config).getRotation());

        return row;
    }
}
