/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenaulihall;

import java.util.Random;
import java.util.concurrent.*;
/**
 *
 * @author owner
 */

public class JudgeClass implements Runnable {

    private String myName;   // used to distinguish threads

    
    public JudgeClass(String name) {
        myName = name;
    }

    public void enter() throws InterruptedException{
       FenauliHall.noJudge.acquire(); // first step
       System.out.println( myName+ "\t has enetered");
       
    }
    public void confirm() throws InterruptedException{   // critical section
            FenauliHall.mutex.acquire();  
            if (FenauliHall.entered > FenauliHall.checked) {
                FenauliHall.mutex.release();
                synchronized(FenauliHall.allSignedIn) {
                    FenauliHall.allSignedIn.wait();
                }
                System.out.println( myName+ "\t has confirmed");
            } else {
                FenauliHall.mutex.release();
            }
            FenauliHall.entered = 0;
            FenauliHall.checked =  0;
            synchronized(FenauliHall.confirmed) {
                FenauliHall.confirmed.notifyAll();
            }
    }
    
    
    public void leave() throws InterruptedException{
        FenauliHall.noJudge.release();
        System.out.println( myName+ "\t has left");
    }     
    
    public void run() {
        try {
        Random rand = new Random();
        Thread.sleep(rand.nextInt(1000));
        enter();
        Thread.sleep(rand.nextInt(1000));
        confirm();
        Thread.sleep(rand.nextInt(1000));
        leave();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}