/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.interfaces;

import java.awt.Graphics2D;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public interface Objected2DObjectFilled extends Objected2DObject {
    public void setFilled ();

    public void setFilled (boolean value);

    public boolean isFilled ();

    public void paint (Graphics2D g);

    public void paintFilled(Graphics2D g);

    public void paintNormal(Graphics2D g);
}
