package de.geisslerbenjamin.jmap.configuration;

import de.geisslerbenjamin.jmap.configuration.interfaces.DataSourceInterface;

/**
 * DataSource (Microsoft Access) configuration.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class DataSource implements DataSourceInterface {
    private String file;
    private String table;

    public DataSource(String file, String table) {
        this.file = file;
        this.table = table;
    }

    @Override
    public String getFile() {
        return file;
    }

    @Override
    public DataSourceInterface setFile(String file) {
        this.file = file;
        return this;
    }

    @Override
    public DataSourceInterface setTable(String table) {
        this.table = table;
        return this;
    }

    @Override
    public String getTable() {
        return table;
    }
}
