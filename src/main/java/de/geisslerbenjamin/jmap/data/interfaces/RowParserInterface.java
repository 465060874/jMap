package de.geisslerbenjamin.jmap.data.interfaces;

import com.healthmarketscience.jackcess.Row;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface RowParserInterface {
    public DrawableInterface read(Row row);

    public DrawableInterface read(AccessRowInterface row);

    public AccessRowInterface create(DrawableConfigurationInterface config);

    public Row update(Row row, DrawableConfigurationInterface config);
}
