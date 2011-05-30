/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.objects.abstractClasses;

import Objected2D.Objected2D;
import Objected2D.Objected2DExtended;
import Objected2D.util.timer.MovementTimer;

/**
 *
 * @author benutzer
 */
public abstract class Objected2DVelocityObject_abstract
        extends Objected2DCollisionObject_abstract {
    private MovementTimer timer;

    private int timerId;

    private int velocity = 0;

    public boolean setParent (Objected2D parent) {
        boolean result = super.setParent(parent);

        /*
         * Set velocity timer
         */
        this.timer = ((Objected2DExtended) parent).getMovementTimer();

        return result;
    }

    public void setVelocity (int velocity) {
        /*
         * Remove if Timer if velocity is null
         */
        if (velocity==0 && this.timer != null) {
            this.timer.delete(this.timerId);
        }

        this.velocity = velocity;

        this.timerId = this.timer.add(this);
    }

    public int getVelocity () {
        return this.velocity;
    }
}
