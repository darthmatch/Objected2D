/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.util.timer.MovementTimer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author benutzer
 */
public class Objected2DExtended extends Objected2D {
    /**
     * Fixed Objects
     */
    protected java.util.LinkedList fixedObjects = new java.util.LinkedList();

    /**
     * Movement timer
     */
    private MovementTimer timer = new MovementTimer(this);

    /**
     * Constructor
     * @param width
     * @param height
     */
    public Objected2DExtended (int width, int height) {
        super(width, height);
    }

    /**
     * Constructor
     * @param dim
     */
    public Objected2DExtended (Dimension dim) {
        super(dim);
    }

    public MovementTimer getMovementTimer () {
        return this.timer;
    }

    /**
     * Add background object
     * @param object
     */
    public void addFixedObject (Objected2DObject object) {
        this.fixedObjects.add(object);
        object.setParent(this);

        this.triggerRerender();
    }

    /**
     * Create Background image
     */
    private void createImage () {
        this.createImageFunc();

        this.renderObjects(
                this.fixedObjects,
                (Graphics2D) this.imageCopy.getGraphics()
        );
    }

    /**
     * Render fixed objects
     */
    public void renderFixedObjects () {
        this.createImage();
    }
}
