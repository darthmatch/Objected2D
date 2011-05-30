/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.abstractClasses;

import Objected2D.objects.interfaces.Objected2DObjectFilled;
import java.awt.Graphics2D;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public abstract class Objected2DVObjectFilled_abstract
        extends Objected2DVelocityObject_abstract
        implements Objected2DObjectFilled {

    boolean filled = false;

    public void setFilled () {
        this.filled = true;
    }

    public void setFilled (boolean value) {
        this.filled = value;
    }

    public boolean isFilled () {
        return this.filled;
    }

    public final void paint (Graphics2D g) {
        if (this.filled)
            this.paintFilled(g);
        else
            this.paintNormal(g);
    }
}
