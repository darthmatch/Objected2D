/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D;

//import Objected2D.

import java.awt.Window;
import Objected2D.util.Mouse;


/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Objected2DWindow extends javax.swing.JFrame {

    protected Objected2DWindowFrame windowFrame;

    protected boolean mouseAlreadySet = false;

    public Objected2DWindow (int width, int height) {
        super();
        this.__construct(width, height, false);
    }

    public Objected2DWindow (int width, int height, boolean extended) {
        super();
        this.__construct(width, height, extended);
    }

    public void __construct (int width, int height, boolean extended) {
        //Größe
        this.setSize(width, height);

        //Close
        this.setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE
                );

        //Zeichenfläche hinzufügen
        this.windowFrame = new Objected2DWindowFrame(extended);
        this.add(this.windowFrame);
        this.setVisible(true);
        this.windowFrame.createObjected2D();

        this.repaint();

        //Anzeigen
        //this.show();
    }

    protected void debug (Object object) {
        System.out.println(String.valueOf(object));
    }

    public void setMouse (Mouse mouse) {
        if (this.mouseAlreadySet)
            throw new RuntimeException("Mouse already set.");

        this.windowFrame.addMouseListener(mouse);
        this.windowFrame.addMouseMotionListener(mouse);
    }

    public Objected2D getObjected2D () {
        return this.windowFrame.objected2D;
    }

    public Objected2DExtended getObjected2DExtended () {
        if (this.windowFrame.extended())
            return (Objected2DExtended) this.windowFrame.objected2D;
        else
            throw new RuntimeException("Objected2DWindow wasn't initalisied in extended Mode");
    }
}
