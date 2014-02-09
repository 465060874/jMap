package de.geisslerbenjamin.jmap.data;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import de.geisslerbenjamin.jmap.data.interfaces.ValidationInterface;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Tests if the table is a valid table in the given Microsoft Access database.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Validation implements ValidationInterface {
    @Override
    public boolean isTableInDatabase(String database, String table) {
        try {
            Database db = DatabaseBuilder.open(new File(database));
            Set<String> tables = db.getTableNames();

            for (String actual : tables) {
                if (actual.equals(table)) {
                    return true;
                }
            }

        } catch (IOException e) {
            return false;
        }

        return false;
    }
}
