/*
Operating System (CS490) Spring 2020, FaneuilHall implementation 
Synchronization Part II, Group 1 
Submitted by : Pooja Khanal, Joshua M Tirone

 */
package faneuilHall;
import java.util.Random;

/**
Immigrant thread implementation
**/
public class ImmigrantsClass implements Runnable{

    private String myName;   // used to distinguish threads 
    
    public ImmigrantsClass(String name) {
        myName = name;
    }
 
    public void enter() throws InterruptedException{
      FeneuilHall.noJudge.acquire();
      FeneuilHall.mutex.acquire(); 
      System.out.println(myName + "\t has entered");
      FeneuilHall.entered++ ;
      FeneuilHall.mutex.release(); 
      FeneuilHall.noJudge.release();
    }
    public void checkIn()throws InterruptedException{
      FeneuilHall.mutex.acquire(); 
      System.out.println(myName + "\t has checked in.");
      FeneuilHall.checked++ ;
      if (FeneuilHall.entered == FeneuilHall.checked){
          synchronized(FeneuilHall.allSignedIn) {
              FeneuilHall.allSignedIn.notify();
          }
      }
      FeneuilHall.mutex.release(); 
    }
    
    
    public void sitDown() throws InterruptedException{
        System.out.println(myName + "\t sat down");
    }
    
    public void swear()throws InterruptedException{
        System.out.println(myName + "\t has sweared");    
    }
    
    public void getCertificate() throws InterruptedException{
        synchronized(FeneuilHall.confirmed) {
            FeneuilHall.confirmed.wait();
        }
        System.out.println(myName + "\t got the certificate");
    }
    
    public void leave() throws InterruptedException{
      FeneuilHall.noJudge.acquire();
      System.out.println(myName+ "\t has left");
      FeneuilHall.noJudge.release();
    }
    public void run() {
        try{
        Random rand = new Random();
        Thread.sleep(rand.nextInt(1000));
        enter();
        Thread.sleep(rand.nextInt(1000));
        checkIn();
//        sitDown();
//        swear();
        
        getCertificate();
        Thread.sleep(rand.nextInt(1000));
        leave();
        }
        catch(InterruptedException ex){
        
        }
    }
    
}
