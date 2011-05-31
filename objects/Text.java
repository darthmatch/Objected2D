/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.objects.abstractClasses.Objected2DCollisionObject_abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.Objected2D;
import Objected2D.objects.abstractClasses.Objected2DVelocityObject_abstract;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 * @todo implement setFont
 */
public class Text
        extends Objected2DVelocityObject_abstract implements Objected2DObject {

    //the displayed text
    String text;

    //dimension
    private int width = -1,
        height = -1;

    public Text (String text, int x, int y) {
        this.__construct(text, x, y, Objected2D.defaultColor);
    }

    public Text (String text, int x, int y, java.awt.Color color) {
        this.__construct(text, x, y, color);
    }

    public void setText (String text) {
        this.text = text;
    }

    protected final void __construct (String text, int x, int y,
            java.awt.Color color) {
        this.text = text;
        this.moveTo(x, y);
    }

    @Override public int getHeight () {
        if (this.height!=-1)
            return this.height;

        this.height = this.getMetricts().getHeight();
        return this.height;
    }

    @Override public int getWidth () {
        if (this.width!=-1)
            return this.width;

        this.width = this.getMetricts().stringWidth(this.text);
        return this.width;
    }

    private java.awt.FontMetrics getMetricts () {
        Graphics2D graphics;
        if (this.getParent()!=null) {
            graphics = this.getParent().getGraphics2d();
        } else {
            graphics = (Graphics2D)
                    new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
                        .getGraphics();
        }

        return graphics.getFontMetrics(graphics.getFont());
    }

    public void paint (java.awt.Graphics2D g) {
        g.drawString(this.text, 0, 0);
    }
}
