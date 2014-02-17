package de.geisslerbenjamin.jkmap.rectangle;

import com.healthmarketscience.jackcess.Row;
import de.geisslerbenjamin.jkmap.data.AccessRow;
import de.geisslerbenjamin.jkmap.data.ParseDrawable;
import de.geisslerbenjamin.jkmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jkmap.rectangle.interfaces.RectangleConfigurationInterface;

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
                        this.getColor(row.get("color").toString(), Double.parseDouble(row.get("transparency").toString())),
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
                        this.getColor(row.getColor(), row.getTransparency()),
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
                .setTransparency(1.0)
                .setType("rectangle")
                .setWidth(((RectangleConfigurationInterface) config).getWidth())
                .setHeight(((RectangleConfigurationInterface) config).getHeight())
                .setRotation(config.getRotation());
    }

    @Override
    public Row update(Row row, DrawableConfigurationInterface config) {
        row.put("id", config.getId());
        row.put("x", config.getX());
        row.put("y", config.getY());
        row.put("width", ((RectangleConfigurationInterface) config).getWidth());
        row.put("height", ((RectangleConfigurationInterface) config).getHeight());
        row.put("rotation", config.getRotation());

        return row;
    }
}
