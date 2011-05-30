/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.util.timer;

import Objected2D.*;
import Objected2D.Objected2DUtils;
import Objected2D.objects.abstractClasses.Objected2DVelocityObject_abstract;
import java.util.ArrayList;

/**
 *
 * @author benutzer
 */
public class MovementTimer {
    /**
     * List of objects
     */
    ArrayList<Objected2DVelocityObject_abstract> objects =
            new ArrayList<Objected2DVelocityObject_abstract>();

    private int count = 0;

    private long timeSinceLastCall = java.lang.System.nanoTime();

    private MovementTimerThread thread;
    
    private Objected2D objected2D;

    public MovementTimer (Objected2D objected2D) {
        this.objected2D = objected2D;
    }

    /**
     * Delete object
     * @param key
     */
    public void delete (int key) {
        this.objects.remove(key);
    }

    /**
     * Add object
     * @return
     */
    public int add (Objected2DVelocityObject_abstract object) {
        this.objects.add(this.count++, object);

        this.run();
        
        return this.count;
    }

    public void run () {
        if (this.thread==null) {
            this.thread = new MovementTimerThread();
            this.thread.start();
            }
        System.out.println("Thread started");
    }

    private class MovementTimerThread extends Thread {
        long currTime;
        long time;

        public void run () {
            while (true) {
                /*
                 * Iterate through all objects
                 */

                //save time in ms
                this.currTime = java.lang.System.nanoTime();
                this.time = (this.currTime - timeSinceLastCall);
                timeSinceLastCall = this.currTime;

                for (Objected2DVelocityObject_abstract object : objects) {
                    System.out.println(((double) (object.getVelocity() * this.time))/1000000000);
                    object.move(((double) (object.getVelocity() * this.time))/1000000000);
                }

                //repaint
                //objected2D.render();
                
                Objected2DUtils.usleep(30);
            }
        }
    }
}
