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
    private String read;
    private String write;

    public DataSource(String file, String read, String write) {
        this.file = file;
        this.read = read;
    }

    @Override
    public String getFile() {
        return file;
    }

    @Override
    public String getTable() {
        return read;
    }
}
