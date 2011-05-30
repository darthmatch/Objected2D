/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.interfaces;

import java.util.LinkedList;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public interface Objected2DCollisionObject extends Objected2DObject {
    public void triggerCollision ();
    public void triggerCollision (boolean value);
    public boolean[][] createCollisionPoints ();
    public boolean[][] getCollisionPoints ();
    public boolean isColliding();
    public boolean isCollidingWith(Objected2DCollisionObject object);
}
