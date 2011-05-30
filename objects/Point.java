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
public class Point extends Rect {
    public Point (int x, int y, java.awt.Color color) {
        super(x, y, 1, 1, true, color);
    }

    public Point (int x, int y) {
        super(x, y, 1, 1, true, Objected2D.defaultColor);
    }
}
