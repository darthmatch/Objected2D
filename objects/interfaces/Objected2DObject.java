/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.interfaces;

import Objected2D.Objected2D;
import Objected2D.Objected2D;
import java.awt.Graphics2D;
/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public interface Objected2DObject
{
      void paint(java.awt.Graphics2D g);

      /*
       * Objected2D Object Public Communication
       */
      boolean inView ();

      boolean inViewX ();

      boolean inViewY ();

      /*
       * Visibility
       */
      boolean isHidden ();

      boolean isVisible ();

      Objected2DObject setHidden ();

      Objected2DObject setVisible ();

      /*
       * Dimensions / Positions
       */
      int getWidth();

      int getHeight();

      int getXPos ();

      int getYPos ();

      int getXPos (int offset);

      int getYPos (int offset);

      /*
       * Color
       */
      void setColor (java.awt.Color color);

      java.awt.Color getColor ();

      /*
       * Movement
       */
      Objected2DObject moveTo (double x, double y);

      Objected2DObject moveToCenter (double x, double y);

      Objected2DObject move (double offset);

      Objected2DObject move (double xOffset, double yOffset);

      /*
       * Objected2D Object Communication -> Internal
       */
      boolean setParent(Objected2D parent);

      void _paint(Graphics2D g);

      /*
       * Etc.
       */
      Objected2DObject clone();

      Objected2DObject clone(int x, int y);
}
