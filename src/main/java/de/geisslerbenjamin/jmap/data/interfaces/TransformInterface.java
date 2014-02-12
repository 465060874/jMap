package de.geisslerbenjamin.jmap.data.interfaces;

import com.healthmarketscience.jackcess.Table;

/**
 * The order of the rows in the Microsoft Access database can be different from the displayed order. To prevent errors
 * the order of the rows is dynamically adapted.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface TransformInterface {
    /**
     * Orders the rows in the real-order of the Microsoft Access database.
     *
     * @param table
     * @param row
     * @return
     */
    public Object[] transferIntoAccessRow(Table table, AccessRowInterface row);
}
