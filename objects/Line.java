/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.objects.abstractClasses.Objected2DCollisionObject_abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.Objected2D;
import Objected2D.objects.abstractClasses.Objected2DVelocityObject_abstract;
import java.awt.Color;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Line
        extends Objected2DVelocityObject_abstract implements Objected2DObject {
    int x1, x2, y1, y2;

    public Line (int x1, int y1, int x2, int y2, java.awt.Color color) {
        this.__construct(x1, y1, x2, y2, color);
    }
    public Line (int x1, int y1, int x2, int y2) {
        this.__construct(x1, y1, x2, y2, Objected2D.defaultColor);
    }
    protected void __construct (int x1, int y1, int x2, int y2, java.awt.Color color) {
        //move forward to the given path
            // (not moveTo -> see / understand self::updateLine)
        this.move(x1, y1);

        //Calculate height
        this.setWidth(Math.abs(x1-x2));
        this.setHeight(Math.abs(y1-y2));
    }

    /**
     * update line to the given
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void updateLine (int x1, int y1, int x2, int y2) {
        this.move((-1) * this.getXPos(), (-1) * this.getYPos());

        this.__construct(x1, y1, x2, y2, Objected2D.defaultColor);
    }

    public void paint (java.awt.Graphics2D g) {
        g.drawLine(
                this.getXPos(),
                this.getYPos(),
                this.getXPos(this.getWidth()),
                this.getYPos(this.getHeight())
        );
    }
}

