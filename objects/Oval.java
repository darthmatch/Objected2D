/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.objects.abstractClasses.Objected2DCollisionObject_abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.Objected2D;
import Objected2D.objects.abstractClasses.Objected2DVelocityObject_abstract;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Oval
        extends Objected2DVelocityObject_abstract implements Objected2DObject {
    //filled oval?
    private boolean filled;

    public Oval (int x, int y, int width, int height, boolean filled,
            java.awt.Color color) {
        this.__constructor(x, y, width, height, filled, color);
    }

    public Oval (int x, int y, int width, int height) {
        this.__constructor(x, y, width, height, false, Objected2D.defaultColor);
    }

    protected final void __constructor (int x, int y, int width, int height,
            boolean filled, java.awt.Color color) {
        this.moveTo(x, y);
        this.setWidth(width);
        this.setHeight(height);
        this.filled = filled;
        this.setColor(color);
    }

    public final void paint (java.awt.Graphics2D g) {
        if (this.filled)
            g.fillOval(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());
        else
            g.drawOval(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());
    }
}