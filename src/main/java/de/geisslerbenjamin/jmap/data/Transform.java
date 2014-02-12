package de.geisslerbenjamin.jmap.data;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Table;
import de.geisslerbenjamin.jmap.data.interfaces.AccessRowInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TransformInterface;

import java.util.Date;

/**
 * The order of the rows in the Microsoft Access database can be different from the displayed order. To prevent errors
 * the order of the rows is dynamically adapted.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Transform implements TransformInterface {
    @Override
    public Object[] transferIntoAccessRow(Table table, AccessRowInterface row) {
        Object[] accessRow = new Object[table.getColumnCount()];
        int position = 0;

        for (Column column : table.getColumns()) {
            switch (column.getName()) {
                case "id":
                    accessRow[position++] = Column.AUTO_NUMBER;
                    break;
                case "x":
                    accessRow[position++] = row.getX();
                    break;
                case "y":
                    accessRow[position++] = row.getY();
                    break;
                case "type":
                    accessRow[position++] = row.getType();
                    break;
                case "color":
                    accessRow[position++] = row.getColor();
                    break;
                case "width":
                    accessRow[position++] = row.getWidth();
                    break;
                case "height":
                    accessRow[position++] = row.getHeight();
                    break;
                case "rotation":
                    accessRow[position++] = row.getRotation();
                    break;
                case "information":
                    accessRow[position++] = "";
                    break;
                case "visible":
                    accessRow[position++] = true;
                    break;
                default:
                    switch (column.getType().toString()) {
                        case "BINARY":
                        case "BOOLEAN":
                        case "FLOAT":
                        case "DOUBLE":
                        case "INT":
                        case "LONG":
                        case "NUMERIC":
                        case "COMPLEX_TYPE":
                            accessRow[position++] = 0;
                            break;
                        case "MEMO":
                        case "TEXT":
                        case "GUID":
                        case "OLE":
                            accessRow[position++] = "";
                            break;
                        case "SHORT_DATE_TIME":
                            accessRow[position++] = new Date();
                            break;
                    }
                    break;
            }
        }

        return accessRow;
    }
}
