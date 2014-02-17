package de.geisslerbenjamin.jkmap.data.interfaces;

/**
 * Tests if the table is a valid table in the given Microsoft Access database.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ValidationInterface {
    /**
     * Tests if the table is a valid table in the given Microsoft Access database.
     *
     * @param database file to the *.mdb file
     * @param table    name of the table
     * @return success or not
     */
    public boolean isTableInDatabase(String database, String table);
}
