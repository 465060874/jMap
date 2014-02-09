package de.geisslerbenjamin.jmap.configuration.interfaces;

/**
 * DataSource (Microsoft Access) configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DataSourceInterface {
    /**
     * The *.mdb file containing the table.
     *
     * @return
     */
    public String getFile();

    /**
     * Set the path to the *.mdb file.
     *
     * @param file
     * @return
     */
    public DataSourceInterface setFile(String file);

    /**
     * The table to read and write to.
     *
     * @return
     */
    public String getTable();

    /**
     * Set the new table to read from and write into.
     *
     * @param table
     * @return
     */
    public DataSourceInterface setTable(String table);
}
