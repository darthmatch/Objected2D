/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.util;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Objected2D.*;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 * @todo detect current mouse pos when mousehandler is set otherwise
 *  position is 0 | 0 on init
 */
public class Mouse implements MouseMotionListener, MouseListener {
    int xPos,
        yPos;

    boolean mouseClicked = false;

    public Mouse () {
    }
    
    public Mouse (Objected2DWindow window) {
        window.setMouse(this);
    }

    public void mouseDragged(MouseEvent event) {

    }

    public void mouseMoved(MouseEvent event) {
        this.xPos = (int) event.getPoint().getX();
        this.yPos = (int) event.getPoint().getY();
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
        this.mouseClicked = true;
    }

    public void mouseReleased(MouseEvent event) {
        this.mouseClicked = false;
    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    /**
     * get xPos of the mouse
     * @return
     */
    public int getxPos() {
        return this.xPos;
    }

    /**
     * get yPos of the mouse
     * @return
     */
    public int getyPos() {
        return this.yPos;
    }

    /**
     * get Pos as Array
     * @return
     */
    public int[] getPosArray () {
        int[] posArray = new int[2];
        posArray[0] = this.xPos;
        posArray[1] = this.yPos;

        return posArray;
    }

    /**
     * get Pos
     * @return
     */
    public Dimension getPos () {
        return new Dimension(this.xPos, this.yPos);
    }

    /**
     * return wheather mouse clicked
     * @return true | false
     */
    public boolean mouseClicked () {
        return this.mouseClicked;
    }
}
