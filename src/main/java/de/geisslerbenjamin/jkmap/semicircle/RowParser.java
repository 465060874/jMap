package de.geisslerbenjamin.jkmap.semicircle;

import com.healthmarketscience.jackcess.Row;
import de.geisslerbenjamin.jkmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;

/**
 * Transfers the data of an Microsoft Access AccessRow into a semi-circle configuration object and the other way around.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class RowParser extends de.geisslerbenjamin.jkmap.circle.RowParser {
    public RowParser(DrawableDisplayConfigurationInterface displayConfig, MediatorInterface mediator) {
        super(displayConfig, mediator);
    }

    @Override
    public DrawableInterface read(Row row) {
        return new SemiCircle(
                new SemiCircleConfiguration(
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
        return new SemiCircle(
                new SemiCircleConfiguration(
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
}
