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
public class Rect
        extends Objected2DVelocityObject_abstract implements Objected2DObject {

    protected int
            x,
            y;

    boolean filled;

    public Rect (int x, int y, int width, int height, boolean filled,
            java.awt.Color color) {
        this.__construct(x, y, width, height, filled, color);
    }

    public Rect (int x, int y, int width, int height) {
        this.__construct(x, y, width, height, false, Objected2D.defaultColor);
    }

    public void __construct (int x, int y, int width, int height,
            boolean filled, java.awt.Color color) {
        this.setColor(color);
        this.filled = true;
        this.moveTo(x, y);
        this.setWidth(width);
        this.setHeight(height);
    }

    /*public void resize (double factor) {
        this.move(
                (int) (this.getWidth()*(1-factor)/2),
                (int) (this.getHeight()*(1-factor)/2)
        );

        this.setWidth((int) (this.getWidth()*factor));
        this.setHeight((int) (this.getHeight()*factor));
    }

    public void resize (int width, int height) {
        this.move(
                (int) (((-1)*this.getWidth()/2)+(width/2)),
                (int) (this.getHeight()*factor/2)
        );

        this.setWidth((int) (this.getWidth()*factor));
        this.setHeight((int) (this.getHeight()*factor));
    }*/

    public void resize (int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public void paint (java.awt.Graphics2D g) {
        if (this.filled)
            g.fillRect(
                    this.getXPos(),
                    this.getYPos(),
                    this.getWidth(),
                    this.getHeight()
                    );
        else
            g.drawRect(
                    this.getXPos(),
                    this.getYPos(),
                    this.getWidth(),
                    this.getHeight()
                    );
    }
}

