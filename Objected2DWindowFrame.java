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
public class Objected2DWindowFrame extends javax.swing.JPanel {
    protected Objected2D objected2D = null;
    protected boolean objected2DAlreadySet = false;
    protected boolean extended;

    //protected

    public Objected2DWindowFrame (boolean extended) {
        this.extended = extended;
        
        //Set to size of parent element
        //this.setSize(dim);

        /*
         * Init Objected Graphics 2D Api
         */
        //this.objected2D =
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

    public void paint(Graphics g) {
        if (this.objected2DAlreadySet) {
            Graphics2D g2d = (Graphics2D) g;

            this.objected2D.render();

            g2d.drawImage(objected2D.getImage(), 0, 0, null);
        }
    }
}
