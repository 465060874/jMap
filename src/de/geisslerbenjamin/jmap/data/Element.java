/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap.data;

import de.geisslerbenjamin.jmap.data.interfaces.DataInterface;
import de.geisslerbenjamin.jmap.data.interfaces.ElementInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;

/**
 *
 * @author Benjamin
 */
public class Element implements ElementInterface {
    private int id;
    private DrawableInterface display;
    private DataInterface data;

    public Element(int id, DrawableInterface display) {
        this.id = id;
        this.display = display;       
    }
    
    public Element(int id, DrawableInterface display, DataInterface data) {
        this.id = id;
        this.display = display;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public DrawableInterface getDisplay() {
        return display;
    }

    public DataInterface getData() {
        return data;
    }        
}
