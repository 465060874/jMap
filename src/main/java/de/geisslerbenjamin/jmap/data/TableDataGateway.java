package de.geisslerbenjamin.jmap.data;

import com.healthmarketscience.jackcess.*;
import de.geisslerbenjamin.jmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowParserInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Access the Access database and read, write und update rows in the table.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class TableDataGateway implements TableDataGatewayInterface {
    private Map<String, RowParserInterface> parser;
    private String file;
    private String read;

    /**
     * Constructor.
     *
     * @param file
     * @param read
     */
    public TableDataGateway(String file, String read) {
        this.file = file;
        this.read = read;
        this.parser = new HashMap<>();
    }

    @Override
    public TableDataGatewayInterface addParser(String type, RowParserInterface parser) {
        this.parser.put(type, parser);
        return this;
    }

    @Override
    public ArrayList<DrawableInterface> findAll() {

        try {
            Database db = DatabaseBuilder.open(new File(this.file));
            Table table = db.getTable(this.read);
            ArrayList<DrawableInterface> result = new ArrayList<>();

            for (Row row : table) {
                if (Boolean.parseBoolean(row.get("visible").toString())) {
                    result.add(this.readRow(row));
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return null;
    }

    @Override
    public DrawableInterface create(String type, DrawableConfigurationInterface drawable) {
        try {
            Database db = DatabaseBuilder.open(new File(this.file));
            Table table = db.getTable(this.read);

            AccessRowInterface row = this.createRow(type, drawable);
            // the order of the columns is a little bit crazy, why ever :-/
            // TODO: Automatic transfer
            /*
            System.out.print(table.getColumns());
            Object[] test = new Object[10];
            test[0] = 1;
            test[1] = 3;

            Object blub = table.addRow(test);
            */

            Object result = table.addRow(
                    Column.AUTO_NUMBER,
                    row.getX(),
                    row.getY(),
                    row.getType(),
                    row.getColor(),
                    true,
                    row.getWidth(),
                    row.getHeight(),
                    row.getRotation(),
                    ""
            );

            // add auto create id
            row.setId(Integer.parseInt(((Object[]) result)[0].toString()));

            return this.readRow(row);
        } catch (Exception error) {
            error.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(String type, DrawableConfigurationInterface drawable) {
        try {
            Database db = DatabaseBuilder.open(new File(this.file));
            Table table = db.getTable(this.read);

            for (com.healthmarketscience.jackcess.Row row : table) {
                if (Integer.parseInt(row.get("id").toString()) == drawable.getId()) {
                    table.updateRow(this.updateRow(type, row, drawable));
                    return true;
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * Create a drawable from a row object.
     *
     * @param row
     * @return
     * @throws Exception
     */
    private DrawableInterface readRow(Row row) throws Exception {
        String type = row.get("type").toString();
        if (this.parser.containsKey(type)) {
            // create the drawable from the row with the specialised parser object
            return this.parser.get(type).read(row);
        }

        throw new Exception("There is no reader for the element type '" + type + "' injected!");
    }

    /**
     * Create a drawable from a row object.
     *
     * @param row
     * @return
     * @throws Exception
     */
    private DrawableInterface readRow(AccessRowInterface row) throws Exception {
        if (this.parser.containsKey(row.getType())) {
            // create the drawable from the row with the specialised parser object
            return this.parser.get(row.getType()).read(row);
        }

        throw new Exception("There is no reader for the element type '" + row.getType() + "' injected!");
    }

    /**
     * Transfer a drawable into a row object.
     *
     * @param type
     * @param config
     * @return
     * @throws Exception
     */
    private AccessRowInterface createRow(String type, DrawableConfigurationInterface config) throws Exception {
        if (this.parser.containsKey(type)) {
            return this.parser.get(type).create(config);
        }

        throw new Exception("There is no reader for the element type '" + type + "' injected!");
    }

    /**
     * Update the elements of a row basing on the drawable object.
     *
     * @param type
     * @param row
     * @param config
     * @return
     * @throws Exception
     */
    private Row updateRow(String type, Row row, DrawableConfigurationInterface config) throws Exception {
        if (this.parser.containsKey(type)) {
            return this.parser.get(type).update(row, config);
        }

        throw new Exception("There is no reader for the element type '" + type + "' injected!");
    }
}
