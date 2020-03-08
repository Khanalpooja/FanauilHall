/*
Operating System (CS490) Spring 2020, FaneuilHall implementation 
Synchronization Part II, Group 1 
Submitted by : Pooja Khanal, Joshua M Tirone

 */
package faneuilHall;

import java.util.Random;

/**
Judge thread implementation 
**/

public class JudgeClass implements Runnable {

    private String myName;   // used to distinguish threads

    public JudgeClass(String name) {
        myName = name;
    }

    public void enter() throws InterruptedException{
       FeneuilHall.noJudge.acquire(); 
       System.out.println( myName+ "\t has entered");
       
    }
    public void confirm() throws InterruptedException{   
            FeneuilHall.mutex.acquire();  
            if (FeneuilHall.entered > FeneuilHall.checked) {
                FeneuilHall.mutex.release();
                synchronized(FeneuilHall.allSignedIn) {
                    FeneuilHall.allSignedIn.wait();
                }
                System.out.println( myName+ "\t has confirmed");
            } else {
                FeneuilHall.mutex.release();
            }
            FeneuilHall.entered = 0;
            FeneuilHall.checked =  0;
            synchronized(FeneuilHall.confirmed) {
                FeneuilHall.confirmed.notifyAll();
            }
    }
    
    
    public void leave() throws InterruptedException{
        FeneuilHall.noJudge.release();
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