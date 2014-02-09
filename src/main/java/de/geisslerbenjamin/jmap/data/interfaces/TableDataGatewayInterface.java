package de.geisslerbenjamin.jmap.data.interfaces;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;

import java.util.ArrayList;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface TableDataGatewayInterface {
    /**
     * Add a parser to transfer the row data into a special implementation of a DrawableConfigurationInterface instance.
     *
     * @param type
     * @param parser
     * @return
     */
    public TableDataGatewayInterface addParser(String type, RowParserInterface parser);

    /**
     * Read all elements from the given table.
     *
     * @return
     */
    public ArrayList<DrawableInterface> findAll();

    /**
     * Add a new row to the access database and return its id.
     *
     * @param type
     * @param drawable
     * @return
     */
    public DrawableInterface create(String type, DrawableConfigurationInterface drawable);

    public boolean update(String type, DrawableConfigurationInterface drawable);

    public boolean delete(int id);
}
