/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objected2D.util.timer;

import Objected2D.Objected2DUtils;
import Objected2D.objects.interfaces.Objected2DObject;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Till Ehrengruber <darthmatch@googlemail.com>
 * @copyright  2011 Till Ehrengruber <darthmatch@googlemail.com>
 */
public class Timer extends Thread {

    protected LinkedList<TimerCallback> callbacks = new LinkedList();

    protected int step;

    protected int startWait;

    protected Hashtable objects = new Hashtable();

    /**
     * Init
     * @param startWait ms to wait before first step
     */
    public Timer (int startWait) {
        this.startWait = startWait;
    }

    /**
     * Add Object to the timer
     * @param object
     * @param wait ms to wait after callback is run
     */
    public void addCallback (TimerCallback object, int wait) {
        this.callbacks.add(object);
    }

    public void setObject (String name, Objected2DObject object) {
        this.objects.put(name, object);
    }

    public Objected2DObject getObject (String name) {
        return (Objected2DObject) this.objects.get(name);
    }

    public void run () {
        Objected2DUtils.usleep(this.startWait);

        Iterator<TimerCallback> iterator = this.callbacks.listIterator();

        while (iterator.hasNext()) {
            this.step++;
            
            iterator.next().invoke(this.step, this.objects);
        }
    }
}
