/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.objects.abstractClasses.Objected2DVObjectFilled_abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import java.awt.Graphics2D;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Polygon
        extends Objected2DVObjectFilled_abstract implements Objected2DObject {

    protected java.awt.Polygon polygon;

    public Polygon (int x, int y, boolean filled) {
        this.moveTo(x, y);
        this.setFilled(filled);
        this.__construct();
    }

    protected void __construct () {
        this.polygon = new java.awt.Polygon();
    }

    public void addPoint (int x, int y) {
        this.polygon.addPoint(x, y);
    }

    public void paintFilled(Graphics2D g) {
            g.fillPolygon(this.polygon);
    }

    public void paintNormal(Graphics2D g) {
            g.drawPolygon(this.polygon);
    }
}
