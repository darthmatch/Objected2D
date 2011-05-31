/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.abstractClasses;

import Objected2D.Objected2D;
import Objected2D.objects.interfaces.Objected2DObject;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public abstract class Objected2DObject_Abstract implements Objected2DObject, Cloneable {
    //component hidden?
    private boolean hidden = false;

    //x position
    private double xPos = 0;

    //y position
    private double yPos = 0;

    //parent object
    private Objected2D parent;

    private boolean parentAlreadySet = false;

    //color of the object
    private java.awt.Color color;

    //direction angle (see self::move(int) )
    private int direction = 0;

    //dimensions
    private int _width,
        _height;

    //internal image representation of the object
    private BufferedImage internalImage;

    //trigger to rerender object
    protected boolean internalImageRerender = true;

    public boolean setParent(Objected2D parent) {
        if (!this.parentAlreadySet) {
            this.parentAlreadySet = true;
            this.parent = parent;
            return true;
        }
        else
            return false;
    }

    public Objected2D getParent() {
        return parent;
    }

    public void setColor (java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color getColor () {
        return this.color;
    }

    public int getXPos () {
        return (int) this.xPos;
    }

    public int getXPos (int offset) {
        return (int) this.xPos+offset;
    }

    public int getYPos () {
        return (int) this.yPos;
    }

    public int getYPos (int offset) {
        return (int) this.yPos+offset;
    }

    static Lock lock = new ReentrantLock();

    /**
     * move forward the given coordinates
     * @param xOffset
     * @param yOffset
     */
    synchronized public Objected2DObject move (double xOffset, double yOffset) {
        this.lock.lock();
        try {
            this.xPos += xOffset;
            this.yPos += yOffset;
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    /*synchronized public Objected2DObject move (int xOffset, int yOffset) {
        return this.move(xOffset, xOffset);
    }*/

    /**
     * move object to given coordinates
     * @param x
     * @param y
     */
    synchronized public Objected2DObject moveTo (double x, double y) {
        this.lock.lock();
        try {
            this.xPos = x;
            this.yPos = y;
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    /**
     * move in the set direction
     * @param xOffset
     * @param yOffset
     */
    synchronized public Objected2DObject move (double offset) {
        return this.move(offset, this.direction);
    }

    /**
     * move in the set direction
     * @param xOffset
     * @param yOffset
     */
    synchronized public Objected2DObject move (double offset, int direction) {
        this.lock.lock();
        try {
            this.xPos += Math.cos(Math.toRadians(direction))*offset;
            this.yPos += Math.sin(Math.toRadians(direction))*offset;
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    synchronized public Objected2DObject moveToCenter (double x, double y) {
        this.lock.lock();
        try {
            this.xPos = x - this.getWidth()/2;
            this.yPos = y - this.getHeight()/2;
        } finally {
            this.lock.unlock();
        }

        return this;
    }

    protected void _checkIfParentSet () {
        if (!this.parentAlreadySet)
            throw new RuntimeException("Object not in a view (see: setParent).");
    }
    /**
     * is the object visible in the parent object
     * @return
     */
    public boolean inView () {
        this._checkIfParentSet();

        if (this.getXPos()+this.getWidth()<this.parent.getWidth()
                && this.getYPos()+this.getHeight()<this.parent.getHeight()
                && this.getXPos() >= 0
                && this.getYPos() >= 0
                )
            return true;
        else
            return false;
    }

    public boolean inViewX () {
        this._checkIfParentSet();
        return this._inViewX();
    }

    public boolean inViewY () {
        this._checkIfParentSet();
        return this._inViewY();
    }

    protected boolean _inViewX () {
        if (this.getXPos()+this.getWidth()<this.parent.getWidth()
                && this.getXPos() >= 0
                )
            return true;
        else
            return false;
    }

    protected boolean _inViewY () {
        if (this.getYPos()+this.getHeight()<this.parent.getHeight()
                && this.getYPos() >= 0
                )
            return true;
        else
            return false;
    }


    /**
     * is the component hidden?
     * @return
     */
    public boolean isHidden () {
        return this.hidden;
    }

    /**
     * is the component visible?
     * @return
     */
    public boolean isVisible () {
        return !this.hidden;
    }

    /**
     * hide component
     */
    public Objected2DObject setHidden () {
        this.hidden = true;

        return this;
    }

    /**
     * display component
     */
    public Objected2DObject setVisible () {
        this.hidden = false;

        return this;
    }

    public int getHeight() {
        return _height;
    }

    public int getWidth() {
        return _width;
    }

    protected void setHeight(int height) {
        this.internalImageRerender = true;

        this._height = height;
    }

    protected void setWidth(int width) {
        this.internalImageRerender = true;
        
        this._width = width;
    }

    public int getDirection() {
        return direction;
    }

    public void changeDirectionTo (int direction) {
        this.rotateTo(direction);

        this.direction = direction;
    }

    public void changeDirection (int direction) {
        
        this.direction += direction;

        if (this.direction > 360)
            this.direction -= 360;

        this.rotateTo(this.direction);
    }

    @Override public Objected2DObject clone () {
        try {
            return (Objected2DObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("CloneNotSupportedException");
        }
    }

    /**
     * clone and move to given positions
     * @param x
     * @param y
     * @return
     */
    public Objected2DObject clone (int x, int y) {
        try {
            Objected2DObject object =
                    (Objected2DObject) super.clone();
            object.moveTo(x, y);

            return  object;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("CloneNotSupportedException");
        }
    }

    /**
     * paint component if visible
     * @param g
     */
    public final void _paint (java.awt.Graphics2D g) {
        if (this.isVisible()) {
            if (this.internalImageRerender) {
                this.renderInternalImage();
            }

            g.drawImage(
                    this.internalImage, null, this.getXPos(), this.getYPos()
                    );
            }
    }

    public void renderInternalImage () {
        this.internalImage = new BufferedImage(
                this.getHeight(), this.getWidth(), BufferedImage.TYPE_INT_ARGB
                );

        Graphics2D g = (Graphics2D) this.internalImage.getGraphics();
        g.setColor(this.color);
        this.paint(g);

        this.internalImageRerender = false;
    }

    public void rotateTo (int degrees) {
        this.renderInternalImage();
        
        this.rotate(direction);
    }

    public void rotate (int degrees) {
        AffineTransform affineTransform = AffineTransform.getRotateInstance(
                Math.toRadians(degrees),
                this.getWidth() / 2,
                this.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(this.getWidth(), this
                .getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) rotatedImage.getGraphics();
        g.setTransform(affineTransform);
        g.drawImage(this.internalImage, 0, 0, null);

        this.internalImage = rotatedImage;
    }

    protected void debug (Object object) {
        System.out.println(String.valueOf(object));
    }
}
