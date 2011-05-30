/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.Objected2D;
import java.util.LinkedList;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Objected2DCollision extends Objected2D {
    /**
     * @param width
     * @param height
     */
    public Objected2DCollision (int width, int height) {
        super(width, height);
    }

    /**
     * @param dim
     */
    public Objected2DCollision (java.awt.Dimension dim) {
        super(dim);
    }

    public void addCollisionObject (Objected2DObject object) {
        this.collisionObjects.add(object);
    }


    
    private LinkedList<Objected2DObject> collisionObjects = new LinkedList();
}
