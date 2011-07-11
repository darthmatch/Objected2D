/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.util;

import Objected2D.Objected2DUtils;
import Objected2D.objects.abstractClasses.Objected2DObject_Abstract;
import Objected2D.objects.interfaces.Objected2DObject;
import Objected2D.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 * @todo: split image in parts and check them first -> performance, points can
 *  returned twice (see boolean below and y offset), collision guess by left,
 *  right, above, below boolean vars
 */
public final class CollisionPointDetector {
    private Objected2DObject object;

    private boolean[][] collisionPoints;

    private BufferedImage image;

    public CollisionPointDetector (Objected2DObject object) {
        /*
         * Clone object
         */
        this.object = object.clone(1, 1);

        /*
         * Paint object
         */
        this.paintObject();

        /*
         * Create Points
         */
        int width = this.image.getWidth();
        int[] imgData = new int[width];
        int[] imgDataLastLine = null;
        int[] imgDataLineBeforeLastLine = null;
        int[] imgCompLine = null;
        this.collisionPoints =
                new boolean[this.image.getWidth()][this.image.getHeight()];

        for (int y = 0; y < image.getHeight(); y++) {
            this.image.getRGB(0, y, width, 1, imgData, 0, 1);

            for (int x = 0; x < width; x++) {
                //object ends (above | below | left | right)
                boolean above = false;
                boolean below1 = false; //1 line below
                boolean below2 = false; //2 line below
                boolean left = false;
                boolean right = false;

                if (imgDataLineBeforeLastLine==null)
                    imgCompLine = imgDataLastLine;
                else
                    imgCompLine = imgDataLineBeforeLastLine;

                //check above
                above = this.checkForCollisionAbove(x,
                            y,
                            imgData,
                            imgDataLastLine,
                            imgDataLineBeforeLastLine
                        );

                //check left
                left = this.checkForCollisionLeft(x,
                            y,
                            imgData,
                            imgDataLastLine,
                            imgDataLineBeforeLastLine
                       );

                //check left
                right = this.checkForCollisionRight(x,
                            y,
                            imgData,
                            imgDataLastLine,
                            imgDataLineBeforeLastLine
                       );

                //check below 1
                below1 = this.checkForCollisionBelow(x,
                            y,
                            imgData,
                            imgDataLastLine
                       );
                
                //check below 2
                below2 = this.checkForCollisionBelow(x,
                            y,
                            imgData,
                            imgDataLineBeforeLastLine
                       );



                
                /*//check above
                if (y==0)
                    above = false;
                else {
                    if (this.getAlphaValue(imgCompLine[x]) == 0 //object in last line invisible
                            && this.getAlphaValue(imgData[x]) > 0)  //object in current line visible
                        above = true;
                    else
                        above = false;
                }

                //check below -> check if current pixel is a change
                if (y == image.getHeight() || imgCompLine == null)
                    below = false;
                else {
                    if (this.getAlphaValue(imgCompLine[x]) > 0 //object in last line invisible
                            && this.getAlphaValue(imgData[x]) == 0)  //object in current line visible
                        below = true;
                    else
                        below = false;
                }

                //check left
                if (x==0 || imgData.length <= (x-1) || (x-2) < 0)
                    left = false;
                else {
                    if (this.getAlphaValue(imgData[x-2]) == 0 //object in last line invisible
                            && this.getAlphaValue(imgData[x]) > 0)  //object in current line visible
                        left = true;
                    else
                        left = false;
                }

                //check right
                if (x == width-1 || imgData.length <= (x+2))
                    right = false;
                else {
                    if (this.getAlphaValue(imgData[x+2]) == 0 //object in last line invisible
                            && this.getAlphaValue(imgData[x]) > 0)  //object in current line visible
                        right = true;
                    else
                        right = false;
                }*/

                this.collisionPoints[x][y]=false;
                
                //create point
                if (above || left || right) {
                    this.collisionPoints[x][y]=true;
                    }

                if (below1) {
                    this.collisionPoints[x][y-1]=true;
                }

                if (below2) {
                    this.collisionPoints[x][y-2]=true;
                }
            }

            if (imgDataLastLine!=null)
                imgDataLineBeforeLastLine=imgDataLastLine;

            imgDataLastLine = imgData.clone();
        }
    }

    protected boolean checkForCollisionAbove (int x, int y, int[] cLine,
            int[] lastLine, int[] lineBeforeLastLine) {
        
        if (y==0) {
            return false;
        }
        else {
            if (this.visible(x, cLine) && (this.inVisible(x, lastLine)
                    || this.inVisible(x, lineBeforeLastLine)))
                return true;
        }

        return false;
    }

    protected boolean checkForCollisionLeft (int x, int y, int[] cLine,
            int[] lastLine, int[] lineBeforeLastLine) {

        if (y==0) {
            if (this.visible(x, cLine))
                return true;
            else
                return false;
        }
        else {
            if (this.visible(x, cLine) && (this.inVisible(x-1, cLine)
                    || this.inVisible(x-2, cLine)))
                return true;
        }

        return false;
    }

    protected boolean checkForCollisionRight (int x, int y, int[] cLine,
            int[] lastLine, int[] lineBeforeLastLine) {

        if (y==0)
            return false;
        else {
            if (this.visible(x, cLine) && (this.inVisible(x+1, cLine)
                    || this.inVisible(x+2, cLine)))
                return true;
        }

        return false;
    }

    protected boolean checkForCollisionBelow (int x, int y, int[] cLine,
            int[] compLine) {

        if (y==0)
            return false;
        else {
            if (this.visible(x, compLine) && this.inVisible(x, cLine))
                return true;
        }

        return false;
    }

    protected boolean inVisible (int x, int[] data) {
        if (data==null || x < 0 || x > data.length)
            return true;

        if (this.getAlphaValue(data[x]) == 0)
            return true;
        else
            return false;
    }

    protected boolean visible (int x, int[] data) {
        if (data==null || x < 0 || x > data.length)
            return false;
        
        if (this.getAlphaValue(data[x]) > 0)
            return true;
        else
            return false;
    }

    private void paintObject () {
        
        this.image = new BufferedImage(
                this.object.getWidth()+2,
                this.object.getHeight()+2,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = (Graphics2D) this.image.createGraphics();

        this.object.paint(g);

        //Objected2DUtils.printImageAlphaRaster(this.image);
    }

    private int getAlphaValue (int rgb) {
        return ((rgb >> 24) & 0xFF);
    }

    public boolean[][] getCollisionPoints () {
        return this.collisionPoints;
    }

    protected void debug (Object object) {
        System.out.println(String.valueOf(object));
    }
}
