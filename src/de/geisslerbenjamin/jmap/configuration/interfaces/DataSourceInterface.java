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
     * The table to read and write to.
     *
     * @return
     */
    public String getTable();
}
