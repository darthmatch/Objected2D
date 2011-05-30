/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

import Objected2D.objects.interfaces.*;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public interface Objected2DObjectContainer {
    public void addObject (Objected2DObject object);
    public java.util.LinkedList getObjects ();
}
