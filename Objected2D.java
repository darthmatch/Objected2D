/**
 * Objected2D
 *
 * @category   Kernel
 * @package    Binary
 * @subpackage esContainer
 * @author     Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2010 Ehrengruber Architekten
 * @license    N/A
 * @version    0.1 alpha
 * @created    06.12.2010
 */

package Objected2D;

import Objected2D.objects.interfaces.Objected2DObject;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 * @todo Objected2DPolygon, Objected2DTimer, Objected2DChart, Objected2DGradient, 
 *  Objected2DStroke, integrate Vertical Retrace for Windows
 *  (if possible for osx + linux)
 */
public class Objected2D implements Objected2DObjectContainer {
    //width
    protected int width;

    //height
    protected int height;

    //image with stored objects
    protected java.awt.image.BufferedImage image;

    //image with background
    protected java.awt.image.BufferedImage imageCopy;

    //graphics
    protected java.awt.Graphics2D graphics;

    protected java.awt.Graphics2D graphicsCopy;

    //autorender after adding / removing component
    //todo: also trigger rerender if component is moved etc.
    protected boolean autoRerender = false;

    //enable antialiasing
    protected boolean antialiasing = true;

    //all objects in the gui
    protected java.util.LinkedList objects = new java.util.LinkedList();

    //default color
    public static java.awt.Color defaultColor = java.awt.Color.black;

    protected boolean saveMemory = false;


    /**
     * @param width
     * @param height
     */
    public Objected2D (int width, int height) {
        this.__constructor(width, height);
    }

    /**
     * @param dim
     */
    public Objected2D (java.awt.Dimension dim) {
        this.__constructor((int) dim.getWidth(), (int) dim.getHeight());
    }

    private void __constructor (int width, int height) {
        /*
         * Save dimensions
         */
        this.width = width;
        this.height = height;

        this.createImage();
    }

    protected void createImageFunc () {
        //Create Image
        this.imageCopy = new java.awt.image.BufferedImage(
                this.width,
                this.height,
                java.awt.image.BufferedImage.TYPE_INT_ARGB
                );

        //save graphics
        //todo: optimize
        this.graphicsCopy = (java.awt.Graphics2D) this.imageCopy.getGraphics();

        //Antialiasing
        if (this.antialiasing)
            this.graphicsCopy.setRenderingHint(
                        java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                    );

        //Fill background
        this.graphicsCopy.setColor(java.awt.Color.WHITE);
        this.graphicsCopy.fillRect(0, 0, this.width, this.height);
        this.graphicsCopy.setColor(java.awt.Color.BLACK);
    }

    private void createImage () {
        this.createImageFunc();
    }

    protected void createImageCopy () {
        this.image = new java.awt.image.BufferedImage(
                this.width,
                this.height,
                java.awt.image.BufferedImage.TYPE_INT_ARGB
                );

        this.imageCopy.copyData(this.image.getRaster());

        this.graphics = (java.awt.Graphics2D) this.image.getGraphics();

        //Antialiasing
        if (this.antialiasing)
            this.graphics.setRenderingHint(
                        java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                    );
    }

    /**
     * fertigstellen
     */
    public void getImageRasterArray () {
        /*
         * Fetch pixel
         */
    }

    public java.awt.image.BufferedImage getImage () {
        return this.image;
    }

    /*public java.awt.image.BufferedImage getOriginImage () {
        return this.originImage;
    }*/

    public java.awt.Graphics2D getGraphics2d () {
        return (java.awt.Graphics2D) this.image.getGraphics();
    }

    /*public void createOriginImageCopy () {
        //this.image = (Objected2DClonableImage) this.originImage.clone();

        int[] srcbuf = ((java.awt.image.DataBufferInt) this.originImage.getRaster().getDataBuffer()).getData();
        int[] dstbuf = ((java.awt.image.DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
        int width = this.originImage.getWidth();
        int height = this.originImage.getHeight();
        int dstoffs = 0;
        int srcoffs = 0;
        for (int y = 0 ; y < height ; y++ , dstoffs+=  this.image.getWidth(), srcoffs += width ) {
            System.arraycopy(srcbuf, srcoffs , dstbuf, dstoffs, width);
        }
    }*/

    /*public void addFixedObject (Objected2DObject object) {
        this.fixedObjects.add(object);

        this.triggerRerender();
    }*/

    /**
     * add objects that can be moved
     * @param object
     */
    public void addObject (Objected2DObject object) {
        this.objects.add(object);
        object.setParent(this);

        this.triggerRerender();
    }

    public java.util.LinkedList getObjects () {
        return this.objects;
    }

    /**
     * trigger rendering if autoRendering is enabled
     * @return
     */
    protected boolean triggerRerender () {
        if (this.autoRerender) {
            this.render();
            return true;
        } else {
            return false;
        }
    }

    protected void renderObjects (java.util.LinkedList objects, java.awt.Graphics2D graphics2d) {
        java.util.Iterator iterator = objects.iterator();

        while (iterator.hasNext()) {
            Objected2DObject object =
                    (Objected2DObject) iterator.next();
            object._paint(graphics2d);
        }
    }

    /**
     * render all objects
     */
    public void render () {
        this.createImageCopy();

        /*
         * Render all Objects
         */
        this.renderObjects(this.objects, this.graphics);

        if (this.saveMemory)
            Runtime.getRuntime().gc();
    }

    /**
     * run garbage collector when rendering
     */
    public void saveMemory () {
        this.saveMemory = true;
    }

    /**
     * enable or disable garbage collection when rendering
     * @param value
     */
    public void saveMemory (boolean value) {
        this.saveMemory = value;
    }

    /**
     * Get width
     * @return int
     */
    public int getWidth () {
        return this.width;
    }
    /**
     * Get height
     * @return int
     */
    public int getHeight () {
        return this.height;
    }

    protected void debug (Object object) {
        System.out.println(String.valueOf(object));
    }
}
