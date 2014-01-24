/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Benjamin
 */
public class Rectangle implements DrawableInterface {
    private double x;
    private double y;
    private double width;
    private double height;
    private Paint color;

    public Rectangle(double x, double y, double width, double height, Paint color) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    public void update(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.color = Color.YELLOW;
        System.out.println("updated x:" + this.x + " y:" + this.y);
    }
    
    @Override
    public Canvas draw()
    {
        System.out.println("draw");
        final Canvas canvas = new Canvas(this.width, this.height);

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(5);
        gc.setStroke(this.color);

        //gc.strokeRect(0, 0, this.width, this.height);
        gc.setFill(this.color);
        gc.fillRect(0,0,this.width, this.height);
        canvas.getTransforms().add(new Rotate(90));

        canvas.setLayoutX(this.x);
        canvas.setLayoutY(this.y);
        canvas.relocate(this.x, this.y);

        
        final Position movedBy = new Position();
        final Rectangle self = this;
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                // x, y
                movedBy.x = canvas.getLayoutX() - t.getSceneX();
                movedBy.y = canvas.getLayoutY() - t.getSceneY();
                canvas.setCursor(Cursor.HAND);
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                canvas.setLayoutX(t.getSceneX() + movedBy.x);
                canvas.setLayoutY(t.getSceneY() + movedBy.y);
                self.update(canvas.getLayoutX(), canvas.getLayoutY());
            }
        });
                
        
        return canvas;
    }
}
