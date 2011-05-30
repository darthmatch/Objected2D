/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects;

import Objected2D.objects.abstractClasses.Objected2DCollisionObject_abstract;
import Objected2D.objects.abstractClasses.Objected2DVelocityObject_abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;


/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 * @todo colormodel from screen -> performance
 */
public class Image
    extends Objected2DVelocityObject_abstract implements Objected2DObject {

    //image data
    protected java.awt.image.BufferedImage img;

    //image already loaded?
    protected boolean loaded = false;

    private float[] scales = { 1.0f, 1.0f, 1.0f, 1.0f };

    private float[] offsets = new float[4];

    RescaleOp rescale;

    /**
     * New Objected2DImage
     * @param src src of the image
     */
    public Image (String src) {
        this.__construct(src, 0, 0);
    }

    /**
     * New Objected2DImage
     * @param src src of the image
     * @param x x position
     * @param y y position
     */
    public Image (String src, int x, int y) {
        this.__construct(src, x, y);
    }

    protected void __construct (String src, int x, int y) {
        this.moveTo(x, y);

        this.updateImageSrc(src);
    }

    public boolean updateImageSrc (String src) {
        try {
            this.img =
                    javax.imageio.ImageIO.read(new java.io.File(src));
            this.loaded = true;
        } catch (java.io.IOException e) {
            this.debug("Objected2DImage::File not found.");
            this.loaded = false;
        }
        return this.loaded;
    }

    public void shapeByObjected2DObject (Objected2DObject object) {
        /*
         * Create Image from Shape
         */
        Objected2DObject objectClone = object.clone();
        objectClone.moveTo(
                this.getWidth()/2-(object.getWidth()/2),
                this.getHeight()/2-(object.getHeight()/2)
                );

        BufferedImage image = new BufferedImage(
                this.img.getWidth(),
                this.img.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = (Graphics2D) image.createGraphics();

        g.setRenderingHint(
                        java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                    );

        objectClone.paint(g);

        int width = this.img.getWidth();
        int[] imgData = new int[width];
        int[] maskData = new int[width];

        for (int y = 0; y < this.img.getHeight(); y++) {
            // fetch a line of data from each image
            this.img.getRGB(0, y, width, 1, imgData, 0, 1);
            image.getRGB(0, y, width, 1, maskData, 0, 1);
            // apply the mask
            for (int x = 0; x < width; x++) {
                imgData[x] = 
                        (imgData[x] & 0x00FFFFFF) + // mask away alpha channel
                        (maskData[x] & 0xFF000000); // shift alpha channel
            }
            // replace the data
            this.img.setRGB(0, y, width, 1, imgData, 0, 1);
        }
    }

    public void setOpacity(float o) {
	this.scales[3] = o;
	rescale = new RescaleOp(this.scales, this.offsets, null);
    }

    public void setRed(float r) {
	this.scales[0] = r;
	rescale = new RescaleOp(this.scales, this.offsets, null);
    }

    public void setGreen(float g) {
	this.scales[1] = g;
	rescale = new RescaleOp(this.scales, this.offsets, null);
    }

    public void setBlue(float b) {
	this.scales[2] = b;
        rescale = new RescaleOp(this.scales, this.offsets, null);
    }

    public void removeAllFilter() {
        this.rescale = null;
    }


    /**
     * scale image
     * @param width width
     * @param height height
     */
    public Image scale (int width, int height) {
        return this.scale((double) width/this.getWidth(),
                (double) height/this.getHeight());
    }

    /**
     * scale image to the given width
     * @param width
     */
    public Image scaleToWidth (int width) {
        return this.scale((double) width/this.getWidth(),
                (double) width/this.getWidth());
    }

    /**
     * scale image to the given height
     * @param height
     */
    public Image scaleToHeight (int height) {
        return this.scale((double) height/this.getHeight(),
                (double) height/this.getHeight());
    }

    /**
     * scale image
     * @param factor factor
     */
    public Image scale (double factor) {
        return this.scale(factor, factor);
    }

    /**
     * scale image
     * @param x factor x
     * @param y factor y
     */
    public Image scale (double x, double y) {
        //scale
        java.awt.geom.AffineTransform at =
            java.awt.geom.AffineTransform.getScaleInstance((double)x,
                (double)y);

        //create new image
        BufferedImage nImage = new BufferedImage(
                (int) (this.img.getWidth()*x),
                (int) (this.img.getHeight()*y),
                BufferedImage.TYPE_INT_ARGB);

        //Get graphics
        java.awt.Graphics2D g = (java.awt.Graphics2D) nImage.getGraphics();

        //draw
        g.drawRenderedImage(this.img, at);

        //save
        this.img = nImage;
        
        return this;
    }

    /**
     * get width of the image
     * @return
     */
    public int getWidth() {
        return this.img.getWidth();
    }

    /**
     * get height of the image
     * @return
     */
    public int getHeight () {
        return this.img.getHeight();
    }

    public void paint (java.awt.Graphics2D g) {
        if (this.loaded)
            g.drawImage(this.img, this.rescale, this.getXPos(), this.getYPos());
        else
            this.debug("Objected2DImage::Image not initalised.");
    }
}
