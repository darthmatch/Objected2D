/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.Objected2DObjectContainer;
import Objected2D.objects.abstractClasses.Objected2DObject_Abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Container
    extends Objected2DObject_Abstract implements Objected2DObject, Objected2DObjectContainer {
    protected java.util.LinkedList<Objected2DObject> objects = new LinkedList();

    public void addObject (Objected2DObject object) {
        this.objects.add(object);
    }

    public java.util.LinkedList getObjects () {
        return this.objects;
    }

    public void paint (Graphics2D g) {
        Iterator<Objected2DObject> iterator = this.objects.listIterator();

        while (iterator.hasNext()) {
            iterator.next()._paint(g);
        }
    }
}
