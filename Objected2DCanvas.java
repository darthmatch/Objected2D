/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

import Objected2D.Objected2D;
import java.awt.*;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Objected2DCanvas extends Canvas {
    protected Objected2D objected2D = null;
    protected boolean objected2DAlreadySet = false;
    protected boolean extended;

    public Objected2DCanvas (boolean extended) {
        this.extended = extended;
    }

    public Objected2DCanvas (int x, int y, boolean extended) {
        this.setSize(x, y);
        this.extended = extended;

        this.createObjected2D();
    }

    public Objected2DCanvas (Dimension dim, boolean extended) {
        this.setSize((int) dim.getWidth(), (int) dim.getHeight());
        this.extended = extended;

        this.createObjected2D();
    }

    public boolean extended () {
        return this.extended;
    }

    public void createObjected2D () {
        if (!this.extended) {
            this.objected2D = new Objected2D(this.getSize());
            this.objected2DAlreadySet = true;
        } else {
            this.objected2D = new Objected2DExtended(this.getSize());
            this.objected2DAlreadySet = true;
        }
        
    }

    public Objected2D getObjected2D () {
        return this.objected2D;
    }

    public void paint(Graphics g) {
        if (this.objected2DAlreadySet) {
            Graphics2D g2d = (Graphics2D) g;

            this.objected2D.render();

            g2d.drawImage(objected2D.getImage(), 0, 0, null);
        }
    }
}
