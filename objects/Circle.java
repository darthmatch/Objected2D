/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.Objected2D;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Circle extends Oval {
    public Circle (int x, int y, int width, boolean filled,
            java.awt.Color color) {
        super(x, y, width, width, filled, color);
    }

    public Circle (int x, int y, int width) {
        super(x, y, width, width, false, Objected2D.defaultColor);
    }
}
