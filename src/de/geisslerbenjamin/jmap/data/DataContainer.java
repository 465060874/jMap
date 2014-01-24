/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap.data;

import de.geisslerbenjamin.jmap.data.interfaces.ElementInterface;
import de.geisslerbenjamin.jmap.drawable.Rectangle;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Benjamin
 */
public class DataContainer {
    private static DataContainer self;
    private ArrayList<ElementInterface> elements;

    private DataContainer() {
        this.elements = new ArrayList<ElementInterface>();
    }
    
    public static DataContainer get() {
        if (self == null) {            
            self = new DataContainer();          
        }
        
        return self;
    }
    
    public DataContainer addElement(ElementInterface element) {
        this.elements.add(element);
        return this;
    }
    
    public ElementInterface getElementById(int id) throws Exception {
        for (ElementInterface element : this.elements) {
             if (element.getId() == id) {
                 return element;
             }   
        }
        
        throw new Exception("Missing element id!");
    }
    
    public ArrayList<ElementInterface> getAll() {
        return this.elements;
    }
}
