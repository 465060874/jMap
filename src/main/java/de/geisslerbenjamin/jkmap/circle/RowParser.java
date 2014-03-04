package de.geisslerbenjamin.jkmap.circle;

import com.healthmarketscience.jackcess.Row;
import de.geisslerbenjamin.jkmap.circle.interfaces.CircleConfigurationInterface;
import de.geisslerbenjamin.jkmap.data.AccessRow;
import de.geisslerbenjamin.jkmap.data.ParseDrawable;
import de.geisslerbenjamin.jkmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;

/**
 * Transfers the data of an Microsoft Access AccessRow into a circle configuration object and the other way around.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class RowParser extends ParseDrawable {
    protected String type;

    /**
     * Constructor.
     *
     * @param displayConfig
     * @param mediator
     */
    public RowParser(DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        super(displayConfig, mediator);
        this.type = "circle";
    }

    @Override
    public DrawableInterface read(Row row) {
        return new Circle(
                new CircleConfiguration(
                        Integer.parseInt(row.get("id").toString()),
                        Double.parseDouble(row.get("x").toString()),
                        Double.parseDouble(row.get("y").toString()),
                        Double.parseDouble(row.get("rotation").toString()),
                        this.getColor(row.get("color").toString(), Double.parseDouble(row.get("transparency").toString())),
                        row.get("information").toString(),
                        Double.parseDouble(row.get("diameter").toString())
                ),
                this.getDisplayConfig(),
                this.getMediator()
        );
    }

    @Override
    public DrawableInterface read(AccessRowInterface row) {
        return new Circle(
                new CircleConfiguration(
                        row.getId(),
                        row.getX(),
                        row.getY(),
                        row.getRotation(),
                        this.getColor(row.getColor(), row.getTransparency()),
                        "",
                        row.getDiameter()
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
                .setRotation(config.getRotation())
                .setColor(config.getColor())
                .setTransparency(1.0)
                .setType(this.type)
                .setDiameter(((CircleConfigurationInterface) config).getDiameter());
    }

    @Override
    public Row update(Row row, DrawableConfigurationInterface config) {
        row.put("id", config.getId());
        row.put("x", config.getX());
        row.put("y", config.getY());
        row.put("diameter", ((CircleConfigurationInterface) config).getDiameter());
        row.put("rotation", config.getRotation());

        return row;
    }
}
